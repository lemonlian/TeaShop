# TeaShop · 收藏的 Spring MVC 茶店项目（fork 存档，不是自己写的）

> 2026-11-15 补写说明：这个仓库 fork 自别人的开源项目，代码原作者不是本人。下面是上游原版 README。

## 一句话
一个卖茶的电商小项目，Spring MVC + MyBatis，亮点是**多数据源动态切换**。

## 它的技术看点
| 文件 | 作用 |
|---|---|
| `DataSourceAspect` | AOP 切面，拦截方法上的注解，切换数据源 |
| `@DataSource` | 自定义注解，标在方法上决定走哪个库 |
| `DynamicDataSource` / `DynamicDataSourceHolder` | 动态数据源与当前线程的数据源标识 |

读写分离、多租户分库这类需求的入门级写法，全在这几个类里。

## 来源
- 上游：`tanwt/TeaShop`（描述原文：一个卖茶的网页代码）
- 当年 fork 的目的：学 AOP + 多数据源怎么组合

## 现在还有用吗
多数据源切换的思路没过时（今天的 Spring 里是 `AbstractRoutingDataSource`），可以当最小示例看。