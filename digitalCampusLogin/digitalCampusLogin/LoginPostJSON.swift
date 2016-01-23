//
//  LoginPostJSON.swift
//  digitalCampusLogin
//
//  Created by Mark MaMian on 16/1/23.
//  Copyright © 2016年 Anit College. All rights reserved.
//

import Foundation
class LoginPostJSON {
    private var _job_number: String!   //job_number ＝ lanling 用户名
    private var _success: Bool!   //success 是网络是否能连接上，并不代表能够登录
    private var _nick_name: String!
    private var _message: String!    //存放返回的消息
    
    var job_number: String{    //三个返回，暂时不明白
        return _job_number
    }
    
    var success: Bool{
        return _success
    }
    
    var nick_name:String{
        return  _nick_name
    }
    
    var message:String{
        return _message
    }
    
    
    
    
    
    init (success: Bool, message: String){  //应该为创建  ，把内部的值转换
        _success = success
        _message = message
    }
}