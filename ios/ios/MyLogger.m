#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(MyLogger, NSObject)

RCT_EXTERN_METHOD(callFunc:(NSInteger *)typeParam)

@end
