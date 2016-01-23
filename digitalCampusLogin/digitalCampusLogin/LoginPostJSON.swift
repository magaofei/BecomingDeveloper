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
    private var _name: String!   //成功否   成功返回1 否则返回0
    private var _nick_name: String!
    
    
    var job_number: String{    //三个返回，暂时不明白
        return _job_number
    }
    
    var name: String{
        return _name
    }
    
    var nick_name:String{
        return  _nick_name
    }
    
    
    
    
    
    init (name: String){  //应该为创建
        _name = name
    }
}