// WARNING
//
// This file has been generated automatically by Visual Studio to store outlets and
// actions made in the UI designer. If it is removed, they will be lost.
// Manual changes to this file may not be handled correctly.
//
using Foundation;
using System.CodeDom.Compiler;

namespace PDFJSViewer.Demo
{
	[Register ("ViewController")]
	partial class ViewController
	{
		[Outlet]
		Xamarin.iOS.PDFJSViewer.PDFJSView OutletPdfJsView { get; set; }

		[Action ("button_presed:")]
		partial void button_presed (Foundation.NSObject sender);
		
		void ReleaseDesignerOutlets ()
		{
			if (OutletPdfJsView != null) {
				OutletPdfJsView.Dispose ();
				OutletPdfJsView = null;
			}
		}
	}
}
