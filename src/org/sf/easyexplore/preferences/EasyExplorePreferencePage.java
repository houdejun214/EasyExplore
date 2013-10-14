/*    */ package org.sf.easyexplore.preferences;
/*    */ 
/*    */ import org.eclipse.jface.preference.FieldEditorPreferencePage;
/*    */ import org.eclipse.jface.preference.StringFieldEditor;
/*    */ import org.eclipse.ui.IWorkbench;
/*    */ import org.eclipse.ui.IWorkbenchPreferencePage;
/*    */ import org.sf.easyexplore.EasyExplorePlugin;
/*    */ 
/*    */ public class EasyExplorePreferencePage extends FieldEditorPreferencePage
/*    */   implements IWorkbenchPreferencePage
/*    */ {
/*    */   public static final String P_TARGET = "org.sf.easyexplore.targetPreference";
/*    */ 
/*    */   public EasyExplorePreferencePage()
/*    */   {
/* 21 */     super(1);
/* 22 */     setPreferenceStore(EasyExplorePlugin.getDefault().getPreferenceStore());
/* 23 */     setDescription("Set up your file explorer application.");
/*    */   }
/*    */ 
/*    */   public void createFieldEditors()
/*    */   {
/* 35 */     addField(new StringFieldEditor("org.sf.easyexplore.targetPreference", "&Target:", getFieldEditorParent()));
/*    */   }
/*    */ 
/*    */   public void init(IWorkbench workbench)
/*    */   {
/*    */   }
/*    */ }

/* Location:           D:\develop\src\EclipseWorkspace110319\easyexplorer\easyexplore.jar
 * Qualified Name:     org.sf.easyexplore.preferences.EasyExplorePreferencePage
 * Java Class Version: 1.2 (46.0)
 * JD-Core Version:    0.5.3
 */