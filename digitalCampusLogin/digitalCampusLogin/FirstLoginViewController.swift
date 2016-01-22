//
//  ViewController.swift
//  digitalCampusLogin
//
//  Created by Mark MaMian on 16/1/21.
//  Copyright © 2016年 Anit College. All rights reserved.
//

import UIKit

class FirstLoginViewController: UIViewController {

    @IBAction func LoginPressLogin(sender: UIButton) {
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()     //系统自带
        
        
        let urlString = "http://123.56.64.138/system/login.do?jobnumber=lanling&password=123456"  //后台网址
        let session = NSURLSession.sharedSession()   //Session方法
        let url = NSURL(string: urlString)!       //设置urlSrting
        
        session.dataTaskWithURL(url) { (data: NSData?, response:  //加了个response:   NSData?前加了个data:
            NSURLResponse?, error: NSError?) -> Void in    //加了个error: 在NSError前
            //方法的具体设置
            
            if let responseData = data {   //必须加do try catch  否则报错
                do{                         //下面代码看起来还有问题
                    let json = try NSJSONSerialization.JSONObjectWithData(responseData, options: NSJSONReadingOptions.AllowFragments)
                    
                    print(json)
                }catch{
                    print("Could not Serialize")
                }
                
            
            }
            
        }.resume()
        
    }
   
    
    
    
    
    
    
   /*override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
    }   //任何一个有人点的动作都可以跳转显示下一个view
*/
    

    
    @IBAction func exitToHome(sender: UIStoryboardSegue) {

        //不需要代码， 这是跳转回来的方法

    
}
}



    
    
    
      
    
    

