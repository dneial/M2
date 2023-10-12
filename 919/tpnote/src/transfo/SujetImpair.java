package transfo;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import Wkfl.Job;
import Wkfl.WkflFactory;
import Wkfl.Workflow;
import Wkfl.Workflows;
import pipelinesA.Pipeline;
import pipelinesA.Stage;

public class SujetImpair {
	
	public static void main(String[] args) {
		Workflows works = FileUtilsA.chargerModeleWkl("model/CircleCI.xmi");
		Pipeline plineA = FileUtilsA.chargerModelePipelineA("model/PipelineA2.xmi");
		System.out.println("noms differents dans workflows: " + checkJobNamesAllDiff(works));
		System.out.println("noms differents dans pipeline: " + checkJobNamesAllDiff(plineA));
		
		Workflows transformed = transformPipeline(plineA);
		String yaml = toYaml(transformed);
		
		System.out.println(yaml);
		
		
	}
	
	
	// question 1
	public static boolean checkJobNamesAllDiff(Workflows works) {
		List<String> jNames = new ArrayList<>();
		for(Workflow work: works.getWorkflows()) {
			for(Job job: work.getJobs()) {
				if(jNames.contains(job.getName())) return false;
				jNames.add(job.getName());
			}
		}
		
		return true;
	}
	
	// question 2
	public static boolean checkJobNamesAllDiff(Pipeline pipe) {
		List<String> jNames = new ArrayList<>();
		List<String> pipeJNames = new ArrayList<>(); 
		Stage stage = pipe.getFirstStage();
		if(stage == null) return true;
		for(pipelinesA.Job job: stage.getJobs()) {
			if(jNames.contains(job.getName())) return false;
			jNames.add(job.getName());
		}
		while(stage.getNextStage() != null) {
			stage = stage.getNextStage();
			for(pipelinesA.Job job: stage.getJobs()) {
				if(jNames.contains(job.getName())) return false;
				jNames.add(job.getName());
			}
		}
		
		return true;
	}
	
	
	// question 3
	public static Workflows transformPipeline(Pipeline pipe) {
		Workflows works = WkflFactory.eINSTANCE.createWorkflows();
		Workflow work = WkflFactory.eINSTANCE.createWorkflow();
		work.setName(String.join("-and-", getStagesNames(pipe)));
		Map<String, List<pipelinesA.Job>> deps = resolveDependencies(pipe);
		
		// debug
//		for(Map.Entry<String, List<pipelinesA.Job>> entry: deps.entrySet()) {
//			System.out.println(entry.getKey() + "\n");
//			for(pipelinesA.Job dep: entry.getValue()) {
//				System.out.println("\t" + dep.getName() + "\n");
//			}
//		}
		
		Map<String, Job> mapJobs = new HashMap<String, Job>();

		for(pipelinesA.Job pJob: getJobs(pipe)) {
			Job wJob = WkflFactory.eINSTANCE.createJob();
			wJob.setName(pJob.getName());
			for(String script: pJob.getScript()) {
				wJob.getSteps().add(script);
			}
			mapJobs.put(wJob.getName(), wJob);
			work.getJobs().add(wJob);
		}
		
		
		
		for(Job j: work.getJobs()) {
			List<pipelinesA.Job> reqs = deps.get(j.getName());
			for(pipelinesA.Job req: reqs) {
				j.getRequires().add(mapJobs.get(req.getName()));
			}
		}
		// debug
//		
//		for(Job j: work.getJobs()) {
//			System.out.println(j.getName());
//			System.out.println("requires: \n");
//			for(Job dep: j.getRequires()) {
//				System.out.println("\t" + dep.getName() + "\n");
//			}
//		}
		
		
		
		
		works.getWorkflows().add(work);
		return works;
	}
	
	public static List<String> getStagesNames(Pipeline pipe){

		List<String> pipeSNames = new ArrayList<>(); 
		Stage stage = pipe.getFirstStage();
		
		if(stage == null) return pipeSNames;
		pipeSNames.add(stage.getName());
		
		while(stage.getNextStage() != null) {
			stage = stage.getNextStage();
			pipeSNames.add(stage.getName());
		}
		return pipeSNames;
	}
	
	public static List<pipelinesA.Job> getJobs(Pipeline pipe){
		List<pipelinesA.Job> jobs = new ArrayList<>();
		Stage stage = pipe.getFirstStage();
		
		if(stage == null) return jobs;
		jobs.addAll(stage.getJobs());
		
		while(stage.getNextStage() != null) {
			stage = stage.getNextStage();
			jobs.addAll(stage.getJobs());
		}
		
		return jobs;
	}
	
	public static Map<String, List<pipelinesA.Job>> resolveDependencies(Pipeline pipe){
		Map<String, List<pipelinesA.Job>> deps = new HashMap<>();
		List<pipelinesA.Job> allJobs = new ArrayList<>();
		Stage stage = pipe.getFirstStage();
		if(stage == null) return deps;
		
		for(pipelinesA.Job pJob: stage.getJobs()) {
			deps.put(pJob.getName(), new ArrayList<>());
		}
		allJobs.addAll(stage.getJobs());

		while(stage.getNextStage() != null) {
			List<pipelinesA.Job> jobsSoFar = new ArrayList<>();
			jobsSoFar.addAll(allJobs);
			stage = stage.getNextStage();
			List<pipelinesA.Job> stageJobs = stage.getJobs();
			for(pipelinesA.Job pJob: stageJobs) {
				deps.put(pJob.getName(), jobsSoFar);
			}
			
			allJobs.addAll(stageJobs);
		}
		
		return deps;
	}

	
	
	// question 4
	public static String toYaml(Workflows works) {
		
		// indent = 1 space
		StringBuilder yaml = new StringBuilder();
		
		for(Workflow work: works.getWorkflows()) {
			yaml.append("jobs:\n");
			for(Job j: work.getJobs()) {
				yaml
					.append(" ")
					.append(j.getName())
					.append(":\n")
					.append("  steps:\n");
				for(String step: j.getSteps()) {
					yaml
						.append("   -")
						.append(step)
						.append("\n");
						
				}
			}
		}
		
		yaml.append("workflows:\n");
		
		for(Workflow work: works.getWorkflows()) {
			yaml
				.append(" ")
				.append(work.getName())
				.append(":\n")
				.append("   jobs:\n");
			for(Job j: work.getJobs()) {
				yaml
					.append("   -")
					.append(j.getName());
				List<Job> reqs = j.getRequires();
				if(!reqs.isEmpty()) {
					yaml.append(":").append("\n    requires:\n");
;
				} else {
					yaml.append("\n");
				}
				for(Job req: reqs) {
					yaml
						.append("     -")
						.append(req.getName())
						.append("\n");
				}
			}
		}
		
		return yaml.toString();
	}
}
