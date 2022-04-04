package Dong_Tai;

import java.util.Arrays;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="Dynamic_programmin",urlPatterns = "/Dynamic_programmin")
/*动态规划算法*/
public class Dynamic_programmin extends HttpServlet {

	long timeStart = System.currentTimeMillis();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			doPost(request, response);//当采用超链方式跳转到Servlet的时候，数据提交方式为GET
		}
		
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
       
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String action=request.getParameter("action");

		//利用动态规划算法解决0-1背包问题
		int value[]= {408,921,1329,11,998,1009,104,839,943,299,374,673,703,954,1657,425,950,1375,430,541,971,332,483,815,654,706,1360,956,992,1948};
		int weight []= {508,1021,1321,111,1098,1196,204,939,1107,399,474,719,803,1054,1781,525,1050,1362,530,641,903,432,583,894,754,806,1241,1056,1092,1545};
		int w = 10149;//背包容量
		int n = weight.length;//表示物品的个数
		int mvaluel[][] = new int[n + 1][w + 1];//最大价值
		int wup[][] = new int[n + 1][w + 1];//放入背包的物品
		int c=0;//记录背包里的物品
		//初始化
		for (int i = 0; i < w + 1; i++) {
			mvaluel[0][i] = 0;
		}
		for (int i = 0; i < n + 1; i++) {
			mvaluel[i][0] = 0;
		}
		//动态规划
		for (int i=1; i <mvaluel.length;i++) {
			for (int j=1; j < mvaluel[i].length; j++) {
				//如果物品的重量小于当前背包的容量
				if (weight[i - 1] > j) {
					mvaluel[i][j] = mvaluel[i - 1][j];
				} else {//如果物品的重量大于当前背包的容量
					//如果上一次最大价值<当前物品的价值+上一次重量-当前物品重量的价值总和。
					if (mvaluel[i - 1][j] < value[i - 1] + mvaluel[i - 1][j - weight[i - 1]]) {
						mvaluel[i][j] = value[i - 1] + mvaluel[i - 1][j - weight[i - 1]];
						wup[i][j] = 1;//将物品放入背包
					} else {
						mvaluel[i][j] = mvaluel[i - 1][j];
					}
				}
			}
		}
		//过程
		for (int arr[] : mvaluel) {
			System.out.println(Arrays.toString(arr));
		}
		//求出最优解
		int a = wup.length - 1;
		int b = wup[0].length - 1;
		for(c=0;c<3;c--) {//只在背包中放入三个物品
			while (a > 0 && b > 0) {
				if (wup[a][b] == 1) {
					out.print("将第" + a + "个商品放入了背包");
					out.print("<br>");
					 b=b- weight[a - 1];
				}
				a--;
			}
		}
		out.print("<br>");
		long timeEnd = System.currentTimeMillis();
		out.print( "总共花费：" + (timeEnd - timeStart)+ "ms，即"+(timeEnd - timeStart)/1000+"s" );
	}

}