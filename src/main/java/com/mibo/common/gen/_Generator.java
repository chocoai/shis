  package com.mibo.common.gen;
  
  import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
  import com.jfinal.plugin.activerecord.generator.Generator;
  import com.jfinal.plugin.druid.DruidPlugin;
  import com.mibo.common.config.SysConfig;
  import com.mibo.common.constant.Global;
  import javax.sql.DataSource;
  
  
  
  
  
  
  public class _Generator
  {
    public static void main(String[] args)
    {
/* 19 */     String baseModelPackageName = "com.mibo.modules.data.base";
      
/* 21 */     String baseModelOutputDir = Global.GEN_MODELPATH + "/src/com/mibo/modules/data/base";
      
/* 23 */     String modelPackageName = "com.mibo.modules.data.model";
      
/* 25 */     String modelOutputDir = baseModelOutputDir + "/../model";
      
/* 27 */     DruidPlugin druidPlugin = getDruidPlugin();
/* 28 */     DataSource data = druidPlugin.getDataSource();
      
/* 30 */     Generator gen = new Generator(data, baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
/* 31 */     _MetaBuilder mb = new _MetaBuilder(data);
      
/* 33 */     mb.setDialect(new MysqlDialect());
      
/* 35 */     gen.setGenerateDaoInModel(true);
      
/* 37 */     gen.setGenerateChainSetter(true);
      
/* 39 */     gen.setGenerateDataDictionary(false);
      
/* 41 */     String prefix = Global.GEN_TABLEPREFIX;
      
/* 43 */     mb.setRemovedTableNamePrefixes(new String[] { prefix });
      
/* 45 */     mb.setTablePrefix(prefix);
      
/* 47 */     gen.setMetaBuilder(mb);
      
/* 49 */     gen.generate();
      
/* 51 */     druidPlugin.stop();
    }
    
  
  
  
  
    public static DruidPlugin getDruidPlugin()
    {
/* 60 */     DruidPlugin druidPlugin = SysConfig.createDruidPlugin();
/* 61 */     druidPlugin.start();
/* 62 */     return druidPlugin;
    }
  }


/* Location:              C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\common\gen\_Generator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */