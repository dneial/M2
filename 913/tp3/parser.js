
import { parse } from 'acorn'
import * as walk from 'acorn-walk'
import { readFileSync, writeFileSync } from 'fs'
import { Visitor } from './visitors.js'


let file = './program.js'
let code = readFileSync(file, 'utf8')
let ast = parse(code, {ecmaVersion: 2020})

writeFileSync('./ast', JSON.stringify(ast.body, null, 2))
let callnodes = []

walk.fullAncestor(ast, (node, state, ancestor) => {
    if (node.type === "CallExpression")
        callnodes.push([node, ancestor])
    }
)


function visit_callnode(nodet) {
    let calledfunc;
    let node = nodet[0]
    let ancestor = nodet[1]
    switch (node.callee.type) {
        case "Identifier":
            calledfunc = node.callee.name
            break;

        case "MemberExpression":
            calledfunc = node.callee.object.name + "." + node.callee.property.name;
            break;
        default:
            calledfunc = "undefined"
            break;
    }
    return calledfunc

}


let calledfuncs = callnodes.map(n => visit_callnode(n))
console.log(calledfuncs)
