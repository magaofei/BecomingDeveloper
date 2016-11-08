//
//  ArticleView.h
//  基础控件实战单元作业
//
//  Created by Mark MaMian on 2016/10/31.
//  Copyright © 2016年 Mark MaMian. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ArticleView : UIView
@property (weak, nonatomic) IBOutlet UILabel *nameLabel;
@property (weak, nonatomic) IBOutlet UILabel *dateLabel;
@property (weak, nonatomic) IBOutlet UILabel *contentLabel;

@end
