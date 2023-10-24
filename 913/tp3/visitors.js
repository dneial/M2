export class Visitor {
    
    constructor(){}


    visitNode(node){
        switch(node.type){
            case "Literal":
                console.log("ici");
                break;
            case "VariableDeclaration":
                let decl = node.declarations
                let name = decl[0].id.name
                let typeValue = decl[0].init.type
                let type;
                if(typeValue === "NewExpression"){
                    type = decl[0].init.callee.name
                }
                if(typeValue === "Literal"){
                    type = typeValue;
                }
                let val = decl[0].init.raw
                console.log(`${name} = ${val} (${type})`);
                break;
            case "FunctionDeclaration":
                console.log(node.id.name + " = function");
                break;
            case "ClassDeclaration":
                console.log("class");
                break;
            case "CallExpression":
                console.log(`calle = {node.callee.name}`);
                console.log
                break;


        }

    }

}
