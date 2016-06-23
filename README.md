
# 动态切换主题
## 想法
一.关闭Activity重新加载，添加渐隐渐现的进出场动画，可以实现单个Activity的无缝的主题切换<br />
二.改变指定View的Background或是TextColor
## 一、实现
1.自定义想要改变的background 或 textColor 或 ActionBar背景 StatusBar的背景等<br />
2.在super和setContentView之间 setTheme()  <br />
3.可以先保存在Shareperence中 重新onCreate时获取就会加载新的主题 <br />
4.给Activity添加进出场动画 渐隐渐现动画比较好  能够实现无缝动态切换 <br />
## 二、实现
1.借鉴 gank.io客户端
2.全部使用自定义View 替换系统的actionBar StatusBar等
3.自己又添加实现ImageView、Button 没有什么技术含量 只求以后用的到



