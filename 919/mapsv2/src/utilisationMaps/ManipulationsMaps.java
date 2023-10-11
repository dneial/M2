package utilisationMaps;

import maps.MapsPackage;
import maps.Pedestrian;
import maps.PublicSpace;
import maps.Road;
import maps.impl.MapsFactoryImpl;
import maps.Garden;
import maps.Map;
import maps.MapsFactory;

import org.eclipse.emf.common.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLMapImpl;


public class ManipulationsMaps {
	private static Map maMap;

	public static void main(String[] args) {
		
		//Je charge l'instance map.xmi du méta-modèle maps.ecore
		Resource resource = chargerModele("model/Map.xmi", MapsPackage.eINSTANCE); // ligne à adapter au nom de votre modèle
		if (resource == null) System.err.println(" Erreur de chargement du modèle");
		//Instruction récupérant le modèle sous forme d'arbre à partir de la classe racine "Map"
		maMap = (Map) resource.getContents().get(0);
		System.out.println("Map: " + maMap.getName());	// affichage du nom de la carte.
		
		printPedestrianRoad();
		getAdjacent("piet");
		printPublicSpaces();
		createJardin("jarden", maMap.getRoads());
		sauverModele("model/jarden.xmi", maMap);
	}
	
	public static Resource chargerModele(String uri, EPackage pack) {
		   Resource resource = null;
		   try {
		      URI uriUri = URI.createURI(uri);
		      Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		      resource = (new ResourceSetImpl()).createResource(uriUri);
		      XMLResource.XMLMap xmlMap = new XMLMapImpl();
		      xmlMap.setNoNamespacePackage(pack);
		      java.util.Map options = new java.util.HashMap();
		      options.put(XMLResource.OPTION_XML_MAP, xmlMap);
		      resource.load(options);
		   }
		   catch(Exception e) {
		      System.err.println("ERREUR chargement du modèle : "+e);
		      e.printStackTrace();
		   }
		   return resource;
		}
	
	public static void sauverModele(String uri, EObject root) {
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

	public static void printRoadNames() {
		for(Road r: maMap.getRoads()) {
			System.out.println("Road " + r.getName());
		}
	}
	
	public static void printPublicSpaces() {
		for(PublicSpace ps: maMap.getSpaces()) {
			System.out.println("Space " + ps.getName());
		}
	}
	
	
	public static void printPedestrianRoad(){
		for(Road r: maMap.getRoads()) {
			if(r instanceof Pedestrian && r.getLength() >= 500) {
				System.out.println(r.getName());
			}
		}
	}
	
	
	public static void getAdjacent(String roadName) {
		for(Road r: maMap.getRoads()) {
			if(r.getName().equals(roadName)) {
				System.out.println(roadName + " borders: ");
				for(PublicSpace border: r.getBorder()) {
					System.out.println(border.getName());
				}
				System.out.println(roadName + " meets: ");
				for(Road meet: r.getMeets()) {
					System.out.println(meet.getName());
				}
			}
		}
	}
	
	public static void createJardin(String name, EList<Road> borders) {
		MapsFactory fac =  MapsFactory.eINSTANCE;
		Garden jarden = fac.createGarden();
		jarden.setName(name);
		jarden.getBorderedBy().addAll(borders);
		// pas besoin ça se fait tout seul
//		for(Road r: borders) {
//			r.getBorder().add(jarden);
//		}
		maMap.getSpaces().add(jarden);
			
	}
}