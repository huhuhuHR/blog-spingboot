springBoot 
===================================  
# ideal 创建很简单的
### 注意启动的application，一定要放在创建的group下面。
>这里是com.huorong

DROP TABLE IF EXISTS  `city`;

CREATE TABLE `city` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '城市编号',
  `province_id` int(10) unsigned  NOT NULL COMMENT '省份编号',
  `city_name` varchar(25) DEFAULT NULL COMMENT '城市名称',
  `description` varchar(25) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

show TABLES ;

INSERT city VALUES (1 ,1,'南京市','哈哈哈哈。');

select * from city;

>http://localhost:8080/api/city?cityName=南京市
##＃
{
  "id": 1,
  "provinceId": 1,
  "cityName": "南京市",
  "description": "哈哈哈哈。"
}