package umlutils;

import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;

public class PackageModif {
	
	public static Model umlP;
	
	public static void main(String[] args) {
		
		umlP = LoadUML.chargerModele("model/InputMove.uml");
		
		

		Package p1 = (Package) umlP.getMember("p1");
		Package p2 = (Package) umlP.getMember("p2");
		
		
		Class a = (Class) p1.getMember("A");
		
		a.setPackage(p2);

		System.out.println(p2.getMembers());
		
		
	}
	
	
	public static void changerPackage(Class c, Package dest) {
		c.setPackage(dest);
	}
	
	
	public Class findClassInPackage(String name, Package p){
        Class c=null;
        for (PackageableElement pd:p.getPackagedElements()){
            if (pd instanceof Class){
                Class pdc=(Class)pd;
                if (pdc.getName().equals(name)){return pdc;}
            } else if (pd instanceof Package) {
                Package pdp=(Package)pd;
                c=findClassInPackage(name, pdp);
                if (c!=null){return c;}
            }
        }
        return c;
    }
    
    public Package findPackageInPackage(String name, Package p){
        Package result=null;
        for (PackageableElement pd:p.getPackagedElements()){
             if (pd instanceof Package) {
                Package pdp=(Package)pd;
                if (pdp.getName().equals(name)){return pdp;}
                result=findPackageInPackage(name, pdp);
                if (result!=null){return result;}
            }
        }
        return result;
    }

	
	

}
