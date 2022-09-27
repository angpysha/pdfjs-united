using System;
using CoreGraphics;
using Foundation;
using ObjCRuntime;
using UIKit;

namespace Xamarin.iOS.PDFJSViewer
{
	// @protocol DocumentLoadedDelegate
	[Protocol(Name = "_TtP11PDFJSReader22DocumentLoadedDelegate_")]
	interface DocumentLoadedDelegate
	{
		// @required -(void)loadedWithObject:(id _Nullable)obj andError:(NSError * _Nullable)error;
		[Abstract]
		[Export("loadedWithObject:andError:")]
		void AndError([NullAllowed] NSObject obj, [NullAllowed] NSError error);
	}

	// @interface PDFJSView : UIView
	[BaseType(typeof(UIView), Name = "_TtC11PDFJSReader9PDFJSView")]
	interface PDFJSView
	{
		// @property (copy, nonatomic) NSURL * _Nullable source;
		[NullAllowed, Export("source", ArgumentSemantic.Copy)]
		NSUrl Source { get; set; }

		// -(instancetype _Nonnull)initWithFrame:(CGRect)frame __attribute__((objc_designated_initializer));
		[Export("initWithFrame:")]
		[DesignatedInitializer]
		IntPtr Constructor(CGRect frame);
	}

	//// @interface PDFJSViewController : UIViewController
	//[BaseType(typeof(UIViewController), Name = "_TtC11PDFJSReader19PDFJSViewController")]
	//interface PDFJSViewController
	//{
	//	[Wrap("WeakWeakDelegate")]
	//	[NullAllowed]
	//	DocumentLoadedDelegate WeakDelegate { get; set; }

	//	// @property (nonatomic, weak) id<DocumentLoadedDelegate> _Nullable weakDelegate;
	//	[NullAllowed, Export("weakDelegate", ArgumentSemantic.Weak)]
	//	NSObject WeakWeakDelegate { get; set; }

	//	//[Wrap("WeakDelegate")]
	//	//[NullAllowed]
	//	//DocumentLoadedDelegate Delegate { get; set; }

	//	//// @property (nonatomic, strong) id<DocumentLoadedDelegate> _Nullable delegate;
	//	//[NullAllowed, Export("delegate", ArgumentSemantic.Strong)]
	//	//NSObject WeakDelegate { get; set; }

	//	// @property (copy, nonatomic) NSURL * _Nullable source;
	//	[NullAllowed, Export("source", ArgumentSemantic.Copy)]
	//	NSUrl Source { get; set; }

	//	// -(void)viewDidLoad;
	//	[Export("viewDidLoad")]
	//	void ViewDidLoad();

	//	// -(instancetype _Nonnull)initWithNibName:(NSString * _Nullable)nibNameOrNil bundle:(NSBundle * _Nullable)nibBundleOrNil __attribute__((objc_designated_initializer));
	//	[Export("initWithNibName:bundle:")]
	//	[DesignatedInitializer]
	//	IntPtr Constructor([NullAllowed] string nibNameOrNil, [NullAllowed] NSBundle nibBundleOrNil);

	//	// -(instancetype _Nullable)initWithCoder:(NSCoder * _Nonnull)coder __attribute__((objc_designated_initializer));
	//	[Export("initWithCoder:")]
	//	[DesignatedInitializer]
	//	IntPtr Constructor(NSCoder coder);
	//}
}
