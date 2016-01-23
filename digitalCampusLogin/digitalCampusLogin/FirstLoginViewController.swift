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
        
        
        let urlString = "http://swapi.co/api/people/1/"  //后台网址
        let session = NSURLSession.sharedSession()   //Session方法
        let url = NSURL(string: urlString)!       //设置urlSrting
        
        session.dataTaskWithURL(url) { (data: NSData?, response:  //加了个response:   NSData?前加了个data:
            NSURLResponse?, error: NSError?) -> Void in    //加了个error: 在NSError前
            //方法的具体设置
            
            if let responseData = data {   //必须加do try catch  否则报错
                do{                         //下面代码看起来还有问题
                    let json = try NSJSONSerialization.JSONObjectWithData(responseData, options: NSJSONReadingOptions.AllowFragments)   // AllowFragments指定顶级节点可以不是数组或字典 。 NSJSONSerialization 是IOS5后苹果提供的API，是JSON的解码框架
                    
                    if let dict = json as? Dictionary<String, AnyObject>{   //开始解析JSON   意思应该是 从字典当中寻找
                        //print("DID WE GET HERE: \(dict.debugDescription)")   // “\” 应当是一个分隔符，用来分割前面的字符串和后面的代码   成功
                        if let name = dict["name"] as? String {   //如果里面有job_name的话就返回
                            
                            let LoginPost = LoginPostJSON(name: name)
                                print(LoginPost.name)
                            
                        }
                    }
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



    
    
    
      
    
    

