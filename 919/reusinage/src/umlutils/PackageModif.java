package umlutils;

import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;

import java.util.List;
import java.util.regex.Pattern;

import javax.lang.model.type.PrimitiveType;
import javax.sound.midi.Soundbank;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.VisibilityKind;

public class PackageModif {
	
	public static Model umlP;
	
	public static void main(String[] args) {
		
		umlP = LoadUML.chargerModele("model/model.uml");
		
		
		PackageModif packageModif = new PackageModif();

		Class carre = packageModif.findClassInPackage("Carre", umlP);
		Class rect = packageModif.findClassInPackage("Rectangle", umlP);
		Class rect2 = packageModif.findClassInPackage("Rectangle2", umlP);
		Class rect3 = packageModif.findClassInPackage("Rectangle3", umlP);
		Class rect4 = packageModif.findClassInPackage("Rectangle4", umlP);
		
		
		System.out.println("carre -> rect1: " + faireRemonter(carre, rect, "aire"));
		System.out.println("carre -> rect2: " + faireRemonter(carre, rect2, "aire"));
		System.out.println("carre -> rect3: " + faireRemonter(carre, rect3, "aire"));
		System.out.println("carre -> rect4: " + faireRemonter(carre, rect4, "aire"));
		
		LoadUML.sauverModele("model/v2.uml", umlP);
		
	}
	
	public void setPublic(String propName, Class c) {
		Property prop = c.getOwnedAttribute(propName, null);
		
		// capitalize nom de property
		String getterName = "get" + Pattern.compile("^.").matcher(propName).replaceFirst(m -> m.group().toUpperCase());
		Operation getter = UMLFactory.eINSTANCE.createOperation();
		getter.setName(getterName);
		getter.setVisibility(VisibilityKind.PUBLIC_LITERAL);
		Parameter param1 = UMLFactory.eINSTANCE.createParameter();
		param1.setDirection(ParameterDirectionKind.RETURN_LITERAL);
		getter.getOwnedParameters().add(param1);
		getter.setIsQuery(true);
		System.out.println(getter.returnResult());
		c.getOwnedOperations().add(getter);
		

		// capitalize nom de property
		String setterName = "set" + Pattern.compile("^.").matcher(propName).replaceFirst(m -> m.group().toUpperCase());
		Operation setter = UMLFactory.eINSTANCE.createOperation();
		setter.setName(setterName);
		setter.setVisibility(VisibilityKind.PUBLIC_LITERAL);
		Parameter param2 = UMLFactory.eINSTANCE.createParameter();
		param2.setName("nom");
		param2.setDirection(ParameterDirectionKind.IN_LITERAL);
		setter.getOwnedParameters().add(param2);
		getter.setType(null);
		c.getOwnedOperations().add(setter);
		
		
	}
	
	public static boolean faireRemonter(Class c, Class superC, String methodName) {
		System.out.println("Refactoring of method " + methodName);
		if(!c.getSuperClasses().contains(superC)) {
			System.out.println("Class " + superC.getName() + " is not superclass of " + c.getName());
			return false;
		}
		
		Operation op = c.getOwnedOperation(methodName, null, null);
		if(op == null) {
			System.out.println("Operation " + methodName + " not found in class " + c.getName());
			return false;
		}
		
		List<Operation> superOps = superC.getOwnedOperations(); 
		Operation superOp = superC.getOwnedOperation(methodName, null, null);
		if(superOps.contains(superOp)) {
			if(superOp.isAbstract()) {
				System.out.println("Operation " + methodName + " is already defined in superclass " + superC.getName());
				return false;
			}
			superOps.add(EcoreUtil.copy(op));
		}
		
		return true;
		
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
