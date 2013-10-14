/*     */ package org.sf.easyexplore.actions;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.text.MessageFormat;
/*     */ import org.eclipse.core.resources.IFile;
/*     */ import org.eclipse.core.resources.IProject;
/*     */ import org.eclipse.core.resources.IResource;
/*     */ import org.eclipse.core.runtime.IAdaptable;
/*     */ import org.eclipse.core.runtime.IPath;
/*     */ import org.eclipse.jdt.core.IJavaProject;
/*     */ import org.eclipse.jdt.internal.core.JarPackageFragmentRoot;
/*     */ import org.eclipse.jdt.internal.core.PackageFragment;
/*     */ import org.eclipse.jface.action.IAction;
/*     */ import org.eclipse.jface.dialogs.MessageDialog;
/*     */ import org.eclipse.jface.viewers.ISelection;
/*     */ import org.eclipse.jface.viewers.IStructuredSelection;
/*     */ import org.eclipse.swt.widgets.Shell;
/*     */ import org.eclipse.ui.IObjectActionDelegate;
/*     */ import org.eclipse.ui.IWorkbenchPart;
/*     */ import org.sf.easyexplore.EasyExplorePlugin;
/*     */ 
/*     */ public class EasyExploreAction
/*     */   implements IObjectActionDelegate
/*     */ {
/*  22 */   private Object selected = null;
/*  23 */   private Class selectedClass = null;
/*     */ 
/*     */   public void setActivePart(IAction action, IWorkbenchPart targetPart)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void run(IAction action)
/*     */   {
/*     */     try
/*     */     {
/*  42 */       if ("unknown".equals(this.selected)) {
/*  43 */         MessageDialog.openInformation(new Shell(), "Easy Explore", "Unable to explore " + this.selectedClass.getName());
/*  44 */         EasyExplorePlugin.log("Unable to explore " + this.selectedClass);
/*  45 */         return;
/*     */       }
/*  47 */       File directory = null;
/*  48 */       if (this.selected instanceof IResource)
/*  49 */         directory = new File(((IResource)this.selected).getLocation().toOSString());
/*  50 */       else if (this.selected instanceof File) {
/*  51 */         directory = (File)this.selected;
/*     */       }
///*  53 */       if (this.selected instanceof IFile) {
///*  54 */         directory = directory.getParentFile();
///*     */       }
///*  56 */       if (this.selected instanceof File) {
///*  57 */         directory = directory.getParentFile();
///*     */       }
/*  59 */       String target = EasyExplorePlugin.getDefault().getTarget();
/*     */ 
/*  61 */       if (!(EasyExplorePlugin.getDefault().isSupported())) {
/*  62 */         MessageDialog.openInformation(new Shell(), "Easy Explore", 
/*  63 */           "This platform (" + 
/*  64 */           System.getProperty("os.name") + 
/*  65 */           ") is currently unsupported.\n" + 
/*  66 */           "You can try to provide the correct command to execute in the Preference dialog.\n" + 
/*  67 */           "If you succeed, please be kind to post your discovery on EasyExplore website http://sourceforge.net/projects/easystruts,\n" + 
/*  68 */           "or by email farialima@users.sourceforge.net. Thanks !");
/*  69 */         return;
/*     */       }
/*     */ 
/*  72 */       if (target.indexOf("{0}") == -1) {
/*  73 */         target = target.trim() + " {0}";
/*     */       }
/*     */ 
/*  76 */       target = MessageFormat.format(target, new String[] { directory.toString() });
/*     */       try
/*     */       {
/*  79 */         EasyExplorePlugin.log("running: " + target);
/*  80 */         Runtime.getRuntime().exec(target);
/*     */       } catch (Throwable t) {
/*  82 */         MessageDialog.openInformation(new Shell(), "Easy Explore", "Unable to execute " + target);
/*  83 */         EasyExplorePlugin.log(t);
/*     */       }
/*     */     } catch (Throwable e) {
/*  86 */       EasyExplorePlugin.log(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void selectionChanged(IAction action, ISelection selection)
/*     */   {
/*     */     try
/*     */     {
/*  95 */       IAdaptable adaptable = null;
/*  96 */       this.selected = "unknown";
/*  97 */       if (selection instanceof IStructuredSelection) {
/*  98 */         adaptable = (IAdaptable)((IStructuredSelection)selection).getFirstElement();
/*  99 */         this.selectedClass = adaptable.getClass();
/* 100 */         if (adaptable instanceof IResource)
/* 101 */           this.selected = ((IResource)adaptable);
/* 102 */         else if ((adaptable instanceof PackageFragment) && (((PackageFragment)adaptable).getPackageFragmentRoot() instanceof JarPackageFragmentRoot))
/* 103 */           this.selected = getJarFile(((PackageFragment)adaptable).getPackageFragmentRoot());
/* 104 */         else if (adaptable instanceof JarPackageFragmentRoot)
/* 105 */           this.selected = getJarFile(adaptable);
/*     */         else
/* 107 */           this.selected = ((IResource)adaptable.getAdapter(IResource.class));
/*     */       }
/*     */     }
/*     */     catch (Throwable e) {
/* 111 */       EasyExplorePlugin.log(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected File getJarFile(IAdaptable adaptable) {
/* 116 */     JarPackageFragmentRoot jpfr = (JarPackageFragmentRoot)adaptable;
/* 117 */     File selected = jpfr.getPath().makeAbsolute().toFile();
/* 118 */     if (!(selected.exists())) {
/* 119 */       File projectFile = new File(jpfr.getJavaProject().getProject().getLocation().toOSString());
/* 120 */       selected = new File(projectFile.getParent() + selected.toString());
/*     */     }
/* 122 */     return selected;
/*     */   }
/*     */ }

/* Location:           D:\develop\src\EclipseWorkspace110319\easyexplorer\easyexplore.jar
 * Qualified Name:     org.sf.easyexplore.actions.EasyExploreAction
 * Java Class Version: 1.2 (46.0)
 * JD-Core Version:    0.5.3
 */