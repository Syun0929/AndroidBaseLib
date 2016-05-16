# AndroidBaseLib
一个android基础类库，集合各种基础的公共操作, 方便开发, 目前只有日志框架

###version

v1.1 初始版本,只有日志框架, 可以打印行号, 点击跳转

v1.2 日志框架添加保存到文件的功能, 使用前先在application全局类初始化日志文件目录

v1.2.2 修复bug

####How to use
[![](https://jitpack.io/v/Syun0929/AndroidBaseLib.svg)](https://jitpack.io/#Syun0929/AndroidBaseLib)

**Step 1**. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
**Step 2.** Add the dependency

	dependencies {
		    compile 'com.github.Syun0929:AndroidBaseLib:v1.3.1'
	}
