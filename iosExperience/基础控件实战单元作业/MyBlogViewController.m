//
//  MyBlogViewController.m
//  基础控件实战单元作业
//
//  Created by Mark MaMian on 2016/10/28.
//  Copyright © 2016年 Mark MaMian. All rights reserved.
//

#import "MyBlogViewController.h"
#import "ArticleView.h"
@interface MyBlogViewController ()
@property (weak, nonatomic) IBOutlet UITextField *textField;
@property (weak, nonatomic) IBOutlet UIImageView *textBgImageView;
@property (weak, nonatomic) IBOutlet UIButton *postButton;
@property (weak, nonatomic) IBOutlet UIView *articleContentView;

@end

@implementation MyBlogViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    UIImage *inputBgImage = [_textBgImageView.image resizableImageWithCapInsets:UIEdgeInsetsMake(12, 22, 12, 22) resizingMode:UIImageResizingModeStretch];
    [_textBgImageView setImage:inputBgImage];
    UIImage *buttonBgImage = [[UIImage imageNamed:@"button-green"] resizableImageWithCapInsets:UIEdgeInsetsMake(12, 22, 12, 22) resizingMode:UIImageResizingModeStretch];
    [_postButton setBackgroundImage:buttonBgImage forState:UIControlStateNormal];


}



- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (IBAction)postButtonAction:(id)sender {
    NSDate *currentDate = [NSDate date];
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"YYYY-MM-dd HH:mm:ss"];
    NSString *timeString = [formatter stringFromDate:currentDate];
    
    [self.view endEditing:YES];//隐藏键盘
    ArticleView *view = [[[UINib nibWithNibName:@"ArticleView" bundle:nil] instantiateWithOwner:nil options:nil] firstObject];
    [view.nameLabel setText:@"我"];
    [view.dateLabel setText:timeString];//显示当前时间
    
    
    
    [view.contentLabel setText:_textField.text];
    [_articleContentView addSubview:view];
    
    [self updateLastArticleViewConstraints];
   
    [view setTransform:CGAffineTransformMakeScale(0, 0)];  //从最小开始缩放
    [UIView animateWithDuration:1.f animations:^{
        [view setTransform:CGAffineTransformIdentity];
    }];
    
}

- (void)updateLastArticleViewConstraints {
    ArticleView *view = [_articleContentView.subviews lastObject];
    [view setTranslatesAutoresizingMaskIntoConstraints:NO];
    [_articleContentView setTranslatesAutoresizingMaskIntoConstraints:NO];
    
    if (_articleContentView.subviews.count == 1) {
        NSLayoutConstraint *topConstraint = [NSLayoutConstraint constraintWithItem:view attribute:NSLayoutAttributeTop relatedBy:NSLayoutRelationEqual toItem:_articleContentView attribute:NSLayoutAttributeTop multiplier:1 constant:12];//上边界约束
        [_articleContentView addConstraint:topConstraint];
        
    } else {
        ArticleView *preArticleView = [_articleContentView.subviews objectAtIndex:_articleContentView.subviews.count - 2];
        NSLayoutConstraint *topConstraint = [NSLayoutConstraint constraintWithItem:view attribute:NSLayoutAttributeTop relatedBy:NSLayoutRelationEqual toItem:preArticleView attribute:NSLayoutAttributeBottom multiplier:1 constant:2];
        [_articleContentView addConstraint:topConstraint];
        
    }
    
    NSLayoutConstraint *leftConstraint = [NSLayoutConstraint constraintWithItem:view attribute:NSLayoutAttributeLeft relatedBy:NSLayoutRelationEqual toItem:_articleContentView attribute:NSLayoutAttributeLeft multiplier:1 constant:0];
    [_articleContentView addConstraint:leftConstraint];
    NSLayoutConstraint *rightConstraint = [NSLayoutConstraint constraintWithItem:view attribute:NSLayoutAttributeRight relatedBy:NSLayoutRelationEqual toItem:_articleContentView attribute:NSLayoutAttributeRight multiplier:1 constant:0];
    [_articleContentView addConstraint:rightConstraint];

    
}

//更新高度
- (void)updateLastArticleViewFrame {
    ArticleView *view = [_articleContentView.subviews lastObject];
    CGFloat offsetY = 0;
    if (_articleContentView.subviews.count == 1) {
        offsetY = 12;
    } else {  //子视图的count大于1
        NSArray *viewArray = _articleContentView.subviews;
        UIView *preView = viewArray[viewArray.count - 2];
//        offsetY = preView.frame.origin.y + preView.frame.size.height + 12;
        offsetY = CGRectGetMaxY(preView.frame) + 12;  //间隔12
    }
    
    CGFloat contentLabelHeight = [view.contentLabel sizeThatFits:CGSizeMake(CGRectGetWidth(_articleContentView.bounds) - 46.f, CGFLOAT_MAX)].height;  //38+8
    CGRect frame = CGRectMake(0, offsetY, CGRectGetWidth(_articleContentView.bounds), 39 + contentLabelHeight);
    [view setFrame:frame];
}

//NSString 方式
- (void)updateLastArticleViewFrame2 {
    ArticleView *view = [_articleContentView.subviews lastObject];
    CGFloat offsetY = 0;
    if (_articleContentView.subviews.count == 1) {
        offsetY = 12;
    } else {  //子视图的count大于1
        NSArray *viewArray = _articleContentView.subviews;
        UIView *preView = viewArray[viewArray.count - 2];
        //        offsetY = preView.frame.origin.y + preView.frame.size.height + 12;
        offsetY = CGRectGetMaxY(preView.frame) + 12;  //间隔12
    }
    
//    CGFloat contentLabelHeight = [view.contentLabel sizeThatFits:CGSizeMake(CGRectGetWidth(_articleContentView.bounds) - 46.f, CGFLOAT_MAX)].height;  //38+8
    CGFloat contentLabelHeight = [view.contentLabel.text boundingRectWithSize:CGSizeMake(CGRectGetWidth(_articleContentView.bounds) - 46.f, CGFLOAT_MAX)
                                                                      options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName:view.contentLabel.font}
                                                                      context:nil].size.height;
    CGRect frame = CGRectMake(0, offsetY, CGRectGetWidth(_articleContentView.bounds), 39 + contentLabelHeight);
    [view setFrame:frame];
}


/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
