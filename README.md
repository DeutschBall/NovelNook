# NovelNook


## 2023.5.17 数据库及对应后端语句修改
AdminMapper.java:
getStaffByUserName(int userid)   deleteStaffByUserName(int userid)   String getPasswordByUsername(int userid)全部依照新版数据库，将参数改为userid

AdminController.java:
login,logout基本无改动，因为与集成版index.html存在冲突，验收时记得将相关部分选择ww的版本（注释掉）

login.html:
测试登录注销功能时写的简陋页面，正式验收时以ww版本为准

LoginHandlerinterceptor.java/MyWebMvcConfig.java:
拦截器部分，若要屏蔽拦截器功能只需注释掉LoginHandlerinterceptor.java中的if()...return false;部分即可（即永远返回TRUE即可）
若不屏蔽拦截器功能，只拦截特定url，则需在MyWebMvcConfig.java中   excludePathPatterns()中加上登录界面所需的静态资源的url即可
以ww版本为准
=======
## 2023 5.8 alipay api

 1. 确保有Alipay、Alipayconfig、Alipaycontroller三个文件，并在之后修改application.yaml

 2. https://natapp.cn/article/natapp_newbie  按这个流程来

    其中   端口为 8080

    ​			config.ini文件只需要写两行：

    ```
    [default]
    authtoken=你的token
    ```

    运行natapp.exe（不要停），拿到一个网址http://example.natappfree.cc

 3. 在resources/application.yaml中，修改alipayconfig.notifyUrl

    ```yaml
    alipayconfig:
      appId: 2021000122683765
      appPrivateKey: xxxxxxxxx不用改
      alipayPublicKey: xxxxxxx不用改
      notifyUrl: http://example.natappfree.cc(你的网址)/alipay/notify     #每个人不一样，要修改
    ```

    修改完后，就不用管这个网址了，相关操作还是在 http://localhost:8080/ 进行，但是natapp.exe要一直运行

    4. http://localhost:8080/alipay/pay?subject=xxxx&traceNo=xxx&totalAmount=xxxx

    通过这个URL跳转到付款，三个参数分别是   订单名;  订单编号;  总金额

    对应returned表，应该是    订单名(随意，建议bookname);   borrowid;   fineamount

    **注意**：订单编号（borrowid）好像不能重复用，每次测试得在returned表里新添一条。猜测一个商家账户的订单编号唯一，

    因此patron组生成borrowid的时候得带点随机性

    5. 跳转到付款界面，可以用账密登录付款（账号：lvslof1406@sandbox.com      密码： 111111）  虚拟金额随便用

    也可以用app”支付宝客户端沙箱版“ 扫码（仅安卓有）

    6. 付款成功，会自动访问一次notifyUrl，并且在returned表中对应这一记录的ispay置为1，前端刷新一次页面重新拿returned表的数据就可以。

    

    

## 2023 5.4  sql修改

```
#5.3 第一次release后变动

#删除user表   		新建 admin表、superuser表
#	patron表 	 userid自增   删掉username
#	staff表 		 username改成userid，自增
#book表 			 bookid自增 添加location字段;   添加book测试数据
#brorrw表、reservation表参照的都是patron的userid


#5.4更改  新增returned表
```

最新版跟release1区别很大，要修改



## 2023.4.18  

Staff组 Release1

1. http://localhost:8080/staff.html  Library staff面板

    back to franpage会转到index.html，可修改为其他页面

2. http://localhost:8080/staff/addMaterial.html 添加书 

    在book表中添加一整行记录

3. http://localhost:8080/staff/deleteMaterial.html 删除书 

    在book表中删除一整行记录

4. http://localhost:8080/staff/returnMaterial.html 还书 

    book表remain+1    borrow表status变为returned

5. http://localhost:8080/staff/patronManage.html patron管理 

    patron账户的展示、增删



## 2023.4.16

数据库设计改动：borrow表和reservation表设计有改动，详情如下

1. borrow表新增borrowid作为主码，不再使用userid, bookid作为主码

```
borrow(borrowid,userid,bookid,borrowtime,deadline,status)
```

2. reservations表新增reservationsid作为主码，不再使用userid, bookid作为主码

```
reservation(reservationid,userid,bookid,reservationtime,status)
```

## 2023.4.13

Staff组使用patron表、book表、borrow表  
为目前所有表建立实体类  
Staff组在线文档[Staff组](https://docs.qq.com/doc/DR2d1aFh6RWJaalVs)


## 2023.4.11

数据库设计有变动,Admin组使用staff表,不再使用user表

```
staff(username,password,firstname,lastname,email,telephone,avatar)
```

Admin组在线文档@[Admin组 (qq.com)](https://docs.qq.com/doc/DZndlc3hrR1Jwd1da?u=d40a8d8e1be54004acb1a14b0d0ad35c)







## 关系数据库设计





数据库名:`novelnook`

共有四个表,`user,book,borrow,reservation`

建库以及建表内容均已在本项目的根目录下的db.sql中写好,请各位开发者自行用mysql的source命令导入

以下是四个数据表的设计

```cassandraq
user(userid,username,password,avatarlocation,userrole,email,phone);
```

> <u>用户id</u>,用户名,密码,头像url位置,用户角色(枚举:Admin,Patron,Staff,SuperUser)
>
> 其中用户头像只是一个url,具体图片可以以静态资源的形式存放于/resources/upload/目录下

```
book(bookid,bookname,press,author,publishtime,catagory,remain,introduction);
```

> <u>书id</u>,书名,出版社,作者,印刷时间,图书类别,剩余数量(全借出降为0),图书摘要

```
borrow(userid,bookid,borrowtime,deadline,status)
```

> <u>用户id,书id</u>,借阅时间,到期时间,借还状态(枚举:已还,未还)

```
reservation(userid,bookid,reservationtime,status)
```

> <u>用户id,书id</u>,图书预约时间,状态(枚举:已经预约到还是仍在预约,还是放弃预约)

## 后端架构
后端采用`SpringBoot`技术,总体上`MVC`三层架构,其中

`Dao`层采用`mybatis`框架,

`Controller`层均使用`RestController`做到前后端分离

静态资源比如前端HTML等资源或者上传目录请在`resources/static`中建立

网站的各种配置在`resources/application.yaml`中,请使用`yaml`文件,不要使用`xml`进行网站配置

尽量采用注解开发,减少使用`xml`配置

后端在`/src/main/java/class4.spm.novelnook/`下,其中

`controller/`是控制层目录,请各位开发者在此处实现`RestController`

`service/`是服务层目录

`mapper/`是数据访问层目录

`pojo/`是对象模型目录

`NovelnookApplication.java`是整个`SpringBoot`项目的入口点


## 前端架构

得找个会前端的来写