# <プロジェクト名: 例 Java GUI Drawing Tool>
これは、Java Swingを使用して作成したシンプルなドローイングツールです。図形の描画、色の変更、ファイルの保存・読み込みなどの機能を備えています。

動作環境
Java Development Kit (JDK) 17 以上

make

# 使い方
1. リポジトリのクローン

git clone <リポジトリのURL>
cd <リポジトリのディレクトリ名>

2. ビルド

make

成功すると、プロジェクト内にbinディレクトリが作成され、コンパイル済みのクラスファイルが格納されます。

3. 実行

java -cp bin MyApplication

4. クリーン (生成ファイルの削除)

make clean
