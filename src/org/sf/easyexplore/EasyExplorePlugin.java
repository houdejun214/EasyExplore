/*     */ package org.sf.easyexplore;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;

/*     */ import org.eclipse.core.resources.IWorkspace;
/*     */ import org.eclipse.core.resources.ResourcesPlugin;
/*     */ import org.eclipse.core.runtime.ILog;
/*     */ import org.eclipse.core.runtime.IPluginDescriptor;
/*     */ import org.eclipse.core.runtime.Status;
/*     */ import org.eclipse.jface.preference.IPreferenceStore;
/*     */ import org.eclipse.ui.plugin.AbstractUIPlugin;
/*     */ 
/*     */ public class EasyExplorePlugin extends AbstractUIPlugin
/*     */ {
/*     */   private static EasyExplorePlugin plugin;
/*     */   private ResourceBundle resourceBundle;
/*     */ 
			public EasyExplorePlugin() {
				plugin = this;
				try {
					this.resourceBundle = ResourceBundle
							.getBundle("org.sf.easyexplore.EasyExplorePluginResources");
				} catch (MissingResourceException localMissingResourceException) {
				}
			}
			public EasyExplorePlugin(IPluginDescriptor descriptor)
/*     */   {
/*  31 */     super(descriptor);
/*  32 */     plugin = this;
/*     */     try {
/*  34 */       this.resourceBundle = ResourceBundle.getBundle("org.sf.easyexplore.EasyExplorePluginResources");
/*     */     }
/*     */     catch (MissingResourceException localMissingResourceException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static EasyExplorePlugin getDefault()
/*     */   {
/*  44 */     return plugin;
/*     */   }
/*     */ 
/*     */   public static IWorkspace getWorkspace()
/*     */   {
/*  51 */     return ResourcesPlugin.getWorkspace();
/*     */   }
/*     */ 
/*     */   public static String getResourceString(String key)
/*     */   {
/*  59 */     ResourceBundle bundle = getDefault().getResourceBundle();
/*  60 */     String res = null;
/*     */     try {
/*  62 */       res = bundle.getString(key);
/*     */     } catch (MissingResourceException localMissingResourceException) {
/*  64 */       res = key;
/*     */     }
/*  66 */     return res;
/*     */   }
/*     */ 
/*     */   public ResourceBundle getResourceBundle()
/*     */   {
/*  73 */     return this.resourceBundle;
/*     */   }
/*     */ 
/*     */   public static void log(Object msg) {
/*  77 */     ILog log = getDefault().getLog();
/*  78 */     Status status = new Status(1, getDefault().getDescriptor().getUniqueIdentifier(), 4, msg + "\n", null);
/*  79 */     log.log(status);
/*     */   }
/*     */ 
/*     */   public static void log(Throwable ex) {
/*  83 */     ILog log = getDefault().getLog();
/*  84 */     StringWriter stringWriter = new StringWriter();
/*  85 */     ex.printStackTrace(new PrintWriter(stringWriter));
/*  86 */     String msg = stringWriter.getBuffer().toString();
/*  87 */     Status status = new Status(4, getDefault().getDescriptor().getUniqueIdentifier(), 4, msg, null);
/*  88 */     log.log(status);
/*     */   }
/*     */ 
/*     */   protected void initializeDefaultPreferences(IPreferenceStore store)
/*     */   {
/*  94 */     String defaultTarget = "shell_open_command {0}";
/*  95 */     String osName = System.getProperty("os.name");
/*  96 */     if (osName.indexOf("Windows") != -1) {
/*  97 */       defaultTarget = "explorer.exe {0}";
/*     */     }
/*  99 */     else if (osName.indexOf("Mac") != -1) {
/* 100 */       defaultTarget = "open {0}";
/*     */     }else if(osName.indexOf("Ubuntu") != -1){
				defaultTarget = "nautilus --browser {0}";
			  }
/*     */ 
/* 103 */     store.setDefault("org.sf.easyexplore.targetPreference", defaultTarget);
/*     */   }
/*     */ 
/*     */   public String getTarget()
/*     */   {
/* 111 */     return getPreferenceStore().getString("org.sf.easyexplore.targetPreference");
/*     */   }
/*     */ 
/*     */   public boolean isSupported()
/*     */   {
/* 120 */     String osName = System.getProperty("os.name");
/*     */ 
/* 122 */     return ((osName.indexOf("Windows") != -1) || 
/* 122 */       (osName.indexOf("Mac") != -1) ||
				(osName.indexOf("Ubuntu") != -1));
/*     */   }
/*     */ }

/* Location:           D:\develop\src\EclipseWorkspace110319\easyexplorer\easyexplore.jar
 * Qualified Name:     org.sf.easyexplore.EasyExplorePlugin
 * Java Class Version: 1.2 (46.0)
 * JD-Core Version:    0.5.3
 */