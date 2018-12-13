  package com.mibo.common.gen;
  
  import com.jfinal.plugin.activerecord.generator.MetaBuilder;
  import com.jfinal.plugin.activerecord.generator.TableMeta;
  import java.sql.ResultSet;
  import java.util.List;
  import javax.sql.DataSource;
  
  public class _MetaBuilder extends MetaBuilder
  {
    public String tablePrefix;
    
    public _MetaBuilder(DataSource dataSource)
    {
/* 15 */     super(dataSource);
    }
    
  
  
  
  
    public void setTablePrefix(String tablePrefix)
    {
/* 24 */     this.tablePrefix = tablePrefix;
    }
    
  
  
  
    public void setRemovedTableNamePrefixes(String... removedTableNamePrefixes)
    {
/* 32 */     this.removedTableNamePrefixes = removedTableNamePrefixes;
    }
    
    protected void buildTableNames(List<TableMeta> ret) throws java.sql.SQLException {
/* 36 */     ResultSet rs = getTablesResultSet();
/* 37 */     while (rs.next()) {
/* 38 */       String tableName = rs.getString("TABLE_NAME");
/* 39 */       if (!this.excludedTables.contains(tableName))
        {
  
/* 42 */         if (!isSkipTable(tableName))
          {
  
/* 45 */           if (tableName.contains(this.tablePrefix))
            {
  
/* 48 */             TableMeta tableMeta = new TableMeta();
/* 49 */             tableMeta.name = tableName;
/* 50 */             tableMeta.remarks = rs.getString("REMARKS");
/* 51 */             tableMeta.modelName = buildModelName(tableName);
/* 52 */             tableMeta.baseModelName = buildBaseModelName(tableMeta.modelName);
/* 53 */             ret.add(tableMeta);
            } } } }
/* 55 */     rs.close();
    }
  }


/* Location:              C:\Users\liqp\Desktop\shis.zip!\shis\WEB-INF\classes\com\mibo\common\gen\_MetaBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */