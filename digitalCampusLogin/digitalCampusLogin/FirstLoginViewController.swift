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
        
        let urlString = "http://123.56.64.138/system/login.do?jobnumber=lanling&password=123456"  //后台网址
        let session = NSURLSession.sharedSession()   //Session方法   iOS8以后才支持
        let url = NSURL(string: urlString)!       //设置urlSrting    设置网址

        session.dataTaskWithURL(url) {(data: NSData?, response:NSURLResponse?, error: NSError?) -> Void in    //加了个error: 在NSError前   //加了个response:   NSData?前加了个data:    从网址当中取数据
            //方法的具体设置
            //let myString = NSString(data:data!, encoding: NSUTF8StringEncoding) as! String    //MARK 什么意思
            //print(myString)//  チェックのため、読み込んだデータをそのまま表示  http://ascii.jp/elem/000/001/044/1044645/index-2.html   解决了UTF的问题
            //直接输出了结果
            if let responseData = data {   //必须加do try catch  否则报错
                do{                         //下面代码看起来还有问题
                    let json = try  NSJSONSerialization.JSONObjectWithData(responseData, options: NSJSONReadingOptions.MutableContainers)   // AllowFragments指定顶级节点可以不是数组或字典 。 NSJSONSerialization 是IOS5后苹果提供的API，是JSON的解码框架
                   
                    
                    if let dict = json as? Dictionary<String, AnyObject>{   //开始解析JSON   意思应该是 从字典当中寻找
                        //print("DID WE GET HERE: \(dict.debugDescription)")   // “\” 应当是一个分隔符，用来分割前面的字符串和后面的代码   成功
                        if let success = dict["success"] as? Bool{   //如果里面有success的话就返回，要说明success 的值， 从LoginPostJSON.swift中调用
                            let LoginPost = LoginPostJSON(success: success)   //解析success
                                print(LoginPost.success)
                                print(NSString(data: data! , encoding: NSUTF8StringEncoding ))  //终于成功
                                print("成功解析")   //解析成功返回
                                
                        }
                        else{
                            print("解析失败")   //解析失败返回
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



    
    
    
      
    
    

