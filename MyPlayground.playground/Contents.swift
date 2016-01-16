//: Playground - noun: a place where people can play

import UIKit

var isMyHouseOnFire:Bool=false
var annotherBool=true

if isMyHouseOnFire {
    print("Someone get me some water!")
    
}else{
    print("Someone get some fire for my house!")
}

var result=true==true
result = true == false
result = false == false

var accountTotal = 300.33
var newCallOfDutyGame = 59.99
if accountTotal >= newCallOfDutyGame{
    print("I just purchased the game!")
    
}else{
    print("I dont have enough money")
}

var myString:String = ""
var myOtherString:String?    //修饰符?将其制定为可选的
var yetAnotherVariable:Float! //修饰符!指定为隐式拆封的


// MARK - <变量声明shuxing，>

for var  count=0;count<50;count=count+1{
    //Do this ,50 times.
}

/* var myMemoryHog: SomeHugeObject ? = SomeHugeObject{}
   myMemoryHog = nil   
*/