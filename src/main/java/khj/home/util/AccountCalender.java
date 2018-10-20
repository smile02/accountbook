package khj.home.util;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import khj.home.vo.Expand;
import khj.home.vo.Income;

public class AccountCalender {
	
	Map<Map<String,Object>, Object> exRegMap = new HashMap<>();
	Map<Map<String,Object>, Object> inRegMap = new HashMap<>();
	StringBuffer sb = new StringBuffer();
	// 미리 기준 연도와 , 기준월을 정해서 출력하는 방법을 선택함.
	
	int base_year = 1980; // 기준 연도
	int base_month = 1; // 기준 월 입니다. 실제로는 1980년 1월 1을 기준으로 계산
	int total_sum = 0; // 기준 년과 월에서 입력받은 날짜까지 총 일수를 여기에 저장

	int[] month_table = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // 각 월마다 총 일수

	public int is_leap_year(int n) { // 윤년이 있는 년도를 조사하는 함수입니다. 윤년인경우는 1

		if (n % 4 == 0) { // 4의 배수는 윤년

			if (n % 100 == 0) // 그런데 100의 배수는 윤년이 아님
			{
				if (n % 400 == 0) // 그중에서 400의 배수는 윤년
					return 1;

				return 0;
			}

			return 1;
		}

		else
			return 0;

	}

	public int total_to_month(int total) // 기준 날짜부터 입력받은 날짜까지 총 일수를 구해서 반환,
	{

		boolean CHK = false; // 무한 루프를 돌리기위한 불리안 값입니다.
		int i = 0; // i변수는 월입니다.
		int cnt = 0; // 총 일수를 월로 바꾸어서 cnt 저장시킵니다.
		int chk_leap_year = base_year; // 총 일수를 월로 바꾸려면 윤년이 있는 날을 고려해야합니다.
		// 기준연도부터 시작해서 윤년을 조사합니다.

		while (CHK != true) {
			if (is_leap_year(chk_leap_year) == 1) // 만약 지금 연도가 윤년이라면
				month_table[1] = 29; // 2월달은 하루가 더 늘어납니다.

			if (total >= month_table[i]) // 총 일수가 month_table[]배열의 총일수 보다 작다면,
			{
				total -= month_table[i++]; // 총일수를 month_table 배열의 현재 월의 일수로 빼줍니다.
											// 그리고 i를 증가시킵니다. 즉 다음달로 증가됩니다.
				cnt++; // 그리고 월이 증가합니다.
				if (i == 12) // 만약 12월이된다면 계절이 변해 다시 제자리로 오는것처럼
				{
					i -= 12; // 다시 12를빼서 0으로 만들어줍니다.
					chk_leap_year++; // 그리고 12개월이 지났으니 연도도 증가시켜줘야함
				}

				month_table[1] = 28; // 윤년을 평년의 해로 바꾸어주어야합니다.

			}

			else
				break;

		}

		cnt %= 12; // 위의 무한루프를 통해 총일수를 총월수로 계산됨, 이제 12 나머지 연산을 해주면
		// 몇년도 몇월이라는 값으로 바꿀수있습니다.

		return (cnt + 1); // 그리고 바꾼 월을 반환

	}

	public int count_leap(int n) // 기준 연도부터 시작해서 입력받은 연도까지의 윤년이 있는날을 셉니다.
	{
		int i; // 기준연도를 저장합니다.
		int cnt = 0; // 윤년의 개수입니다.

		for (i = base_year; i < (base_year - n); i++)
		// i(기준연도) 부터 입력받은 연도까지 i를 증가시키며 윤년이낀 갯수를 구함.
		{

			if (is_leap_year(i) == 1) // 위에서 구현한 윤년인지를 판단하는 함수를 사용
			{
				cnt++; // 윤년이라면 총 윤년의 개수를 증가시킵니다.
			}

		}

		return cnt; // 카운트한 윤년의 갯수를 리턴합니다.

	}

	public void convert_to_day(int nYear) { // 기준 연도부터 입력받은 연도까지 총일수를 구해서 리턴합니다.
		total_sum = ((nYear - base_year) * 365) + count_leap((base_year - nYear));
	}

	//로그인을 했을 때의 달력
	public String result(int nYear, int mth, List<Expand> expand, List<Income> income, String diffPrice) {
		boolean todayCheck = false;
		Calendar cal = Calendar.getInstance( );
		int compareDate = cal.get(Calendar.DATE);
		String background = "";
		String red = "color:red;"; //th태그
		String blue = "color:blue;"; //th태그
		DecimalFormat df = new DecimalFormat("#,###");
		
		sb.setLength(0);
		int i, j; // 카운트를 위한 변수입니다.
		int month;

//		int dy = count_leap(base_year - nYear); // dy는 기준연도부터 현재연도까지 낀 윤년의 갯수입니다.
		convert_to_day(nYear); // ★ 우선 기준연도부터 현재 연도까지 년 단위로 총일수를 구합니다. ★

		int day; // 이변수는 요일을 결정하기위해 존재합니다.예를들어 기준일부터 현재일까지 차이가 7이고
		// 기준일이 월요일이면 7로 나눠서 나머지가 0이되니까 월요일임을 알수 있듯이
		// day는 숫자로서 요일을 결정할수있습니다.
		if (nYear >= base_year) {

			if (is_leap_year(nYear) == 1) // 윤달이 낀날의 2월은 하루 증가
				month_table[1] = 29;

			for (i = 0; i < (mth - base_month); i++)
				total_sum += month_table[i];
			// 위에 ★ 에서 기준연도부터 현재연도까지의 일수를 구했습니다.
			// 이 for루프를 통해 나머지 기준월부터 현재월까지의 총일수를 구합니다.
			// 즉 이루프를 통해 기준 연도와 월부터 현재 연도와 월까지의 총일수를 구함.

			day = (total_sum + 2) % 7;
			// 현재까지의 총일수를 7의 나머지로 연산해줍니다. 2를 더해준 이유는 1980년도 1월 1일 = 화요일

			month = total_to_month(total_sum); // 입력받은 해당 날짜의 정확한 달을 구해서 저장합니다.
			sb.append("<table class='table table-bordered'>");
			
			sb.append(				
			"<caption class='text-muted text-center'>"
			+"<button id='leftBtn' type='button' class='btn btn-default'><span class='glyphicon glyphicon-triangle-left'></span></button>"
			+"<button id='topBtn' type='button' class='btn btn-default'><span class='glyphicon glyphicon-triangle-top'></span></button> &nbsp;&nbsp;&nbsp;&nbsp;"
			+"<span id='sendYear'>"+nYear+"</span>년"+"<span id='sendMonth'>"+month+"</span>월의달력~ &nbsp;&nbsp;&nbsp;&nbsp;"
			+"<button id='bottomBtn' type='button' class='btn btn-default'><span class='glyphicon glyphicon-triangle-bottom'></span></button>"
			+"<button id='rightBtn' type='button' class='btn btn-default'><span class='glyphicon glyphicon-triangle-right'></span></button>&nbsp;&nbsp;"
			+"<button class='btn btn-warning' type='button' data-toggle='modal' data-target='#changeModal' data-backdrop='false'>날짜변경</button>&nbsp;&nbsp;"
			+"<button id='today' class='btn btn-default' type='button'>오늘날짜</button>&nbsp;"
			+"<span id='diffPrice' class='cotrol-label' style='display:inline-block; margin-left:5px; cursor:pointer;'>잔액 : <strong id='b_Price'>"+df.format(Integer.parseInt(diffPrice))+"</strong>원</span>"
			+"</caption>");
			
			//System.out.println(+month + " 월의 달력");
			sb.append("<thead>");
			sb.append("<tr> <th class='text-center' style='"+red+"'>일</th> <th class='text-center'>월</th> <th class='text-center'>화</th> <th class='text-center'>수</th> <th class='text-center'>목</th> <th class='text-center'>금</th> <th class='text-center' style='"+blue+"'>토</th> </tr>");
			//System.out.println("일 월     화     수     목     금     토");
			sb.append("</thead>");
			sb.append("<tbody><tr style='height:120px;'>");
			for (i = 0; i < day; i++) // day 변수는 요일 입니다. 갯수만큼 \t로 공백을 만들어줍니다.
				//System.out.print("\t");
			{	
				sb.append("<td>\t</td>");
			}
			//수입목록 달력에 나오도록
			Map<String, Object> ex_regdate = new HashMap<>();
			Map<String, Object> in_regdate = new HashMap<>();
			
			for (j = 1; j <= month_table[month - 1]; j++)
			// month변수에서 1을 빼준이유는 배열은 0부터 시작하기때문입니다.		
			{
				String color = ""; //td태그
				switch(day) {
					case 0: if(j == 1 || j == 8 || j == 15 || j == 22 || j == 29) { color = "color:red;";}
							if(j == 7 || j == 14 || j == 21|| j ==28) {color = "color:blue;";}
							break;
					case 1: if(j == 6 || j == 13 || j ==20 || j == 27) { color="color:blue;";}
							if(j == 7 || j == 14 || j == 21 || j == 28) { color="color:red;";}
							break;
					case 2: if(j == 5 || j == 12 || j == 19 || j == 26) { color="color:blue;"; }
							if(j == 6 || j == 13 || j == 20 || j == 27) {color="color:red;";}
							break;
					case 3: if(j == 4 || j == 11 || j == 18 || j == 25) { color="color:blue;";}
							if(j == 5 || j == 12 || j == 19 || j == 26) { color="color:red;";}
							break;
					case 4: if(j == 3 || j == 10 || j == 17 || j == 24 || j == 31) { color="color:blue;";}
							if(j == 4 || j == 11 || j == 18 || j == 25) { color="color:red;";}
							break;
					case 5: if(j == 2 || j == 9 || j == 16 || j == 23 || j == 30) { color="color:blue;";}
							if(j == 3 || j == 10 || j == 17 || j == 24 || j == 31) { color="color:red;";}
							break;
					case 6: if(j == 1 || j == 8 || j == 15 || j == 22 || j == 29) { color="color:blue;";}
							if(j == 2 || j == 9 || j == 16 || j == 23 || j == 30) { color="color:red;";}
							break;
				}
				
				if(j == compareDate) {
					background = "background-color:#FFFF96;";
				}else {
					background = "";
				}
				//System.out.print(j + "\t");// j를 증가시켜가며 차례데로 날짜를 출력합니다.
				for(Expand e : expand) {
					String ex_reg = e.getRegdate();
					ex_reg = ex_reg.replace("-0", "-");
					e.setRegdate(ex_reg);

					if(e.getRegdate().equals(nYear+"-"+month+"-"+j)) {
						ex_regdate.put("regdate", e.getRegdate());
						exRegMap.put(ex_regdate, e.getCount());
					}
				}
				
				for(Income in : income) {
					String in_reg = in.getRegdate();
					in_reg = in_reg.replace("-0", "-");
					in.setRegdate(in_reg);
					
					if(in.getRegdate().equals(nYear+"-"+month+"-"+j)) {
						in_regdate.put("regdate", in.getRegdate());
						inRegMap.put(in_regdate, in.getCount());
					}
				}
				
				//지출, 수입에 있는 날짜와 달력에서 불러온 날짜가 같은경우
				if((nYear+"-"+month+"-"+j).equals(ex_regdate.get("regdate")) && (nYear+"-"+month+"-"+j).equals(in_regdate.get("regdate"))) {
					
					sb.append("<td style='width:100px;"+background+" "+color+"' class='text-center'>"+j
							+"<br/><button id='"+ex_regdate.get("regdate")
							+"' type='button' class='btn btn-danger btn-xs' style='width:60px;' onclick='expandBtn(this);' data-toggle='modal' data-target='#expandModal' data-backdrop='false'>지출 : "
							+exRegMap.get(ex_regdate)
							+"개 </button><br/>"
							+"<button id="+in_regdate.get("regdate")+" type='button' class='btn btn-primary btn-xs' style='width:60px;' onclick='incomeBtn(this);' data-toggle='modal' data-target='#incomeModal' data-backdrop='false'>수입 : "+inRegMap.get(in_regdate)+"개 </button></td>");
							//System.out.println("기준날짜 : "+nYear+"-"+month+"-"+j);
							//System.out.println("지출날짜 : "+ex_regdate+", 갯수 : "+exRegMap.get(ex_regdate));
							//System.out.println("수입날짜 : "+in_regdate+", 갯수 : "+inRegMap.get(in_regdate));
					//지출만 같은경우
				}else if((nYear+"-"+month+"-"+j).equals(ex_regdate.get("regdate"))){
					sb.append("<td style='width:100px;"+background+" "+color+"' class='text-center'>"+j
							+"<br/><button id='"+ex_regdate.get("regdate")
							+"' type='button' class='btn btn-danger btn-xs' style='width:60px;' onclick='expandBtn(this);' data-toggle='modal' data-target='#expandModal' data-backdrop='false'>지출 : "
							+exRegMap.get(ex_regdate)
							+"개 </button>");
				//수입만 같은경우
				}else if((nYear+"-"+month+"-"+j).equals(in_regdate.get("regdate"))) {
					sb.append("<td style='width:100px;"+background+" "+color+"' class='text-center'>"+j
							+"<br/><button id='"+in_regdate.get("regdate")
							+"' type='button' class='btn btn-primary btn-xs' style='width:60px;' onclick='incomeBtn(this);' data-toggle='modal' data-target='#incomeModal' data-backdrop='false'>수입 : "
							+inRegMap.get(in_regdate)
							+"개 </button>");
				//둘 다 아닌경우
				}else {
					sb.append("<td style='width:100px;"+background+" "+color+"' class='text-center'>"+j
							+"<br/><button type='button' class='btn btn-default' style='width:60px; "+background+"; border: none;' disabled='disabled'></button>"+"</td>");
				}
				
				if (((j + day) % 7) == 0)
					//System.out.println();
				{
					sb.append("<tr/>");
					sb.append("<tr style='height:120px;'>");
				}
				// 그리고 처음 요일을 출력하기위한 공백만큼 계산해서 출력
			}
			sb.append("</tbody></table>");
			//System.out.println();
			//sb.append("");

			month_table[1] = 28; // 윤년이었다면 다시 평년으로 바꾸어줍니다.

		}
		return sb.toString();
	}
	
	public String result(int nYear, int mth) {
		sb.setLength(0);
		int i, j; // 카운트를 위한 변수입니다.
		int month;

//		int dy = count_leap(base_year - nYear); // dy는 기준연도부터 현재연도까지 낀 윤년의 갯수입니다.
		convert_to_day(nYear); // ★ 우선 기준연도부터 현재 연도까지 년 단위로 총일수를 구합니다. ★

		int day; // 이변수는 요일을 결정하기위해 존재합니다.예를들어 기준일부터 현재일까지 차이가 7이고
		// 기준일이 월요일이면 7로 나눠서 나머지가 0이되니까 월요일임을 알수 있듯이
		// day는 숫자로서 요일을 결정할수있습니다.
		if (nYear >= base_year) {

			if (is_leap_year(nYear) == 1) // 윤달이 낀날의 2월은 하루 증가
				month_table[1] = 29;

			for (i = 0; i < (mth - base_month); i++)
				total_sum += month_table[i];
			// 위에 ★ 에서 기준연도부터 현재연도까지의 일수를 구했습니다.
			// 이 for루프를 통해 나머지 기준월부터 현재월까지의 총일수를 구합니다.
			// 즉 이루프를 통해 기준 연도와 월부터 현재 연도와 월까지의 총일수를 구함.

			day = (total_sum + 2) % 7;
			// 현재까지의 총일수를 7의 나머지로 연산해줍니다. 2를 더해준 이유는 1980년도 1월 1일 = 화요일

			month = total_to_month(total_sum); // 입력받은 해당 날짜의 정확한 달을 구해서 저장합니다.
			sb.append("<table class='table table-bordered'>");
			sb.append("<caption class='text-muted text-center'>"+nYear+"년 "+month+"월의 달력~ &nbsp;&nbsp;&nbsp;&nbsp;<button id='regChange' class='btn btn-default' type='button' onclick='regChange();'>날짜변경</button></caption>");
			//System.out.println(+month + " 월의 달력");
			sb.append("<thead>");
			sb.append("<tr> <th class='text-center'>일</th> <th class='text-center'>월</th> <th class='text-center'>화</th> <th class='text-center'>수</th> <th class='text-center'>목</th> <th class='text-center'>금</th> <th class='text-center'>토</th> </tr>");
			//System.out.println("일 월     화     수     목     금     토");
			sb.append("</thead>");
			sb.append("<tbody><tr style='height:70px;'>");
			for (i = 0; i < day; i++) // day 변수는 요일 입니다. 갯수만큼 \t로 공백을 만들어줍니다.
				//System.out.print("\t");
			{	
				sb.append("<td>\t</td>");
			}
			
			for (j = 1; j <= month_table[month - 1]; j++)
			// month변수에서 1을 빼준이유는 배열은 0부터 시작하기때문입니다.
			{
				//System.out.print(j + "\t");// j를 증가시켜가며 차례데로 날짜를 출력합니다.
				
				
				sb.append("<td style='width:100px;' id='date_"+j+"' class='text-center'>"+j+"</td>");
				
				if (((j + day) % 7) == 0)
					//System.out.println();
				{
					sb.append("<tr/>");
					sb.append("<tr style='height:70px;'>");
				}
				// 그리고 처음 요일을 출력하기위한 공백만큼 계산해서 출력
			}
			sb.append("</tbody></table>");
			//System.out.println();
			//sb.append("");

			month_table[1] = 28; // 윤년이었다면 다시 평년으로 바꾸어줍니다.

		}
		return sb.toString();
	}

}
