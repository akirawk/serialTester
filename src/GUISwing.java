
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class GUISwing{
   public static void View() {

      //フレーム表示
      JFrame f = new JFrame("テキストエリアの作成");

	  //コンテンツ区画の取得
	  Container cont =  f.getContentPane();

	  //テキストエリアの作成
	  JTextArea jta = new JTextArea("ようこそはじめてのJava入門へ",3,20);

	  //テキストエリアをコンテンツ区画に追加する
	  cont.add(jta);
	  //フレームのサイズを指定
      f.setSize(200, 200);
	  //フレームの表示・非表示を指定
      f.setVisible(true);
	  //×を押した時の処理
	  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }
}

