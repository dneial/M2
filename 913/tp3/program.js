function add(a, b) {
    return a + b
    
}

class P {
    foo(){
        add(1, 2)
    }
}



let a = 123
let b = 3
let c = add(a, b)

let p = new P()

p.foo()

if(a) console.log(add(a, b))
