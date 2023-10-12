package transfo;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLMapImpl;

import Wkfl.WkflPackage;
import Wkfl.Workflows;
import pipelinesA.Pipeline;
import pipelinesA.PipelinesAPackage;

public class FileUtilsA {
	
	
	
	
	public static void sauverModeleWkf(String uri, Workflows root) {
		   Resource resource = null;
		   try {
		      URI uriUri = URI.createURI(uri);
		      Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		      resource = (new ResourceSetImpl()).createResource(uriUri);
		      resource.getContents().add(root);
		      resource.save(null);
		   } catch (Exception e) {
		      System.err.println("ERREUR sauvegarde du modèle : "+e);
		      e.printStackTrace();
		   }
		}
	
	public static Pipeline chargerModelePipelineA(String uri) {
				Resource resource = null;
				   try {
				      URI uriUri = URI.createURI(uri);
				      Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
				      resource = (new ResourceSetImpl()).createResource(uriUri);
				      XMLResource.XMLMap xmlMap = new XMLMapImpl();
				      xmlMap.setNoNamespacePackage(PipelinesAPackage.eINSTANCE);
				      java.util.Map options = new java.util.HashMap();
				      options.put(XMLResource.OPTION_XML_MAP, xmlMap);
				      resource.load(options);
				   }
				   catch(Exception e) {
				      System.err.println("ERREUR chargement du modèle : "+e);
				      e.printStackTrace();
				   }
				   return (Pipeline) resource.getContents().get(0);
				}
	
	
	public static Workflows chargerModeleWkl(String uri) {
		Resource resource = null;
		   try {
		      URI uriUri = URI.createURI(uri);
		      Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		      resource = (new ResourceSetImpl()).createResource(uriUri);
		      XMLResource.XMLMap xmlMap = new XMLMapImpl();
		      xmlMap.setNoNamespacePackage(WkflPackage.eINSTANCE);
		      java.util.Map options = new java.util.HashMap();
		      options.put(XMLResource.OPTION_XML_MAP, xmlMap);
		      resource.load(options);
		   }
		   catch(Exception e) {
		      System.err.println("ERREUR chargement du modèle : "+e);
		      e.printStackTrace();
		   }
		   return (Workflows) resource.getContents().get(0);
		}
}