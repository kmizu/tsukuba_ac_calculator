# tsukuba_ac_calculator

筑波大学AC入試（2002年度入学）に提出した数式計算プログラムです。当時の[@kmizu](https://github.kmizu)がどんなコードを書いていたか振り返るために、プログラムに存在するバグもそのまま残してありますがご容赦ください。

## 実行方法

当時はJava Appletだったものの、今はJava Appletをそのままだと動かせないのでSwingを使った形に修正してあります。

```sh
$ mvn exec:java -Dexec.mainClass=com.gihub.kmizu.tsukuba_ac_calculator.Calculator
```

## スクリーンショット

![スクリーンショット](https://user-images.githubusercontent.com/97326/202017486-538009fe-277b-4f59-9336-30d065988b41.jpg)
