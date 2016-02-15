//
//  ViewController.swift
//  digitalCampusLogin
//
//  Created by Mark MaMian on 16/1/21.
//  Copyright © 2016年 Anit College. All rights reserved.
//

import UIKit
import Alamofire

class FirstLoginViewController: UIViewController {

    @IBAction func LoginPressLogin(sender: UIButton) {
        
    }

    override func viewDidLoad() {
        super.viewDidLoad()     //系统自带
        
        
        
        Alamofire.request(.GET, "http://123.56.64.138/system/login.do?jobnumber=lanling&password=123456", parameters: ["foo": "bar"])
            .responseJSON { response in
             
                print(response.result)   // result of response serialization
                
                if let JSON = response.result.value {
                    print(JSON)
                    
                }
                //解决解码问题失败，先解决存储问题
        }
        
            

    }
    
    

    
    @IBAction func exitToHome(sender: UIStoryboardSegue) {

        //不需要代码， 这是跳转回来的方法

    
}
}



    
    
    
      
    
    

