//
//  main.m
//  JSONOC
//
//  Created by Mark MaMian on 16/5/20.
//  Copyright © 2016年 Mark MaMian. All rights reserved.
//

#import <Foundation/Foundation.h>

int main(int argc, const char * argv[]) {
    @autoreleasepool {
        // insert code here...
        NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL
        URLWithString:@"http://api.twitter.com/1/statuses/user_timeline.json?
        screen_name=jadoon88"]];
                                                              
        NSData *response = [NSURLConnection sendSynchronousRequest:request
        returningResponse:nil error:nil];
                                                              
        NSError *jsonParsingError = nil;
        NSArray *publicTimeline = [NSJSONSerialization JSONObjectWithData:response options:0 error:&amp jsonParsingError];
                                                              
        NSDictionary *tweet;
																															
				for(int i=0; i&lt;[publicTimeline count];i++){
				tweet= [publicTimeline objectAtIndex:i];
				NSLog(@”Statuses: %@”, [tweet objectForKey:@"text"]);
			  }
										
																															
																													
																															
        NSLog(@"Hello, World!");
    }
    return 0;
}
