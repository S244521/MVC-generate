package packageCode.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("tab")
@JsonIgnoreProperties(ignoreUnknown = true) // 忽略JSON中未知的字段
public class TabEntity{
//@TableId(type = IdType.AUTO)
//生成成员变量声明
private Integer id;
private String tabName;

//添加无参构造函数
//public Tab() {}

//添加带参构造函数，处理最后一个参数的逗号问题
//public Tab(Integer id,String tabName) {
//    //        this.id = id;
//    //        this.tabName = tabName;
//    //    }
}