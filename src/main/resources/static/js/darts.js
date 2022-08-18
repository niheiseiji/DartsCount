

// UIと得点の対応表
function initScoreArray() {
	let scores = [
	  { id: 0, name: 'OUT', score: 0},
	  { id: 50, name: 'S-BULL', score: 50 },
	  { id: 51, name: 'D-BULL', score: 50 },
	];
	
	for(i=1; i<=20; i++) {
		scores.push({id: i, name: i, score: i});
	}
	return scores;	
}

// 初期化
const roundThrow = 3;
const scores = initScoreArray(); // jsの配列は更新可能なので注意
let totalScore = 0;
let currentRound = 1;
let currentThrow = 0;
let roundScores = [];
let throwScores = [];// カウントアップなら最大長24 [150,100,...]

// scoreボタンをクリックしたときの処理
function scoresClick(e){
	if (currentThrow > 23) {
		//console.log("###END###")
		return;
	}

  	const id = e.target.id; //クリック要素のid
	const score = scores.find((v) => v.id == id);// スコア表からスコアを特定
	throwScores[currentThrow] = score.score;// 一投スコア更新
	roundScores = calcRoundScore(throwScores);// ラウンドスコア更新
	currentRound = calcCurrentRound(currentThrow);
	document.getElementById(`r${currentRound}-score`).innerHTML=roundScores[currentRound];

  	totalScore = calcTotalScore();
	document.getElementById("total-score").innerHTML=totalScore;// 総得点ラベル更新

	++currentThrow;	// 投数の加算

	if (currentThrow < 24) {
		currentRound = calcCurrentRound(currentThrow);
		document.getElementById("current-round").innerHTML=currentRound+"/8";		
	}

	// form生成

	// 終了判定
	if(currentThrow == 24) {
		document.getElementById("finish-btn").disabled = false;
	}
}

// scoreボタンをクリックしたときの処理
function timesClick(e){
	if (currentThrow > 24) {
		return;
	}
	
	let targetThrow = currentThrow - 1;

  	const id = e.target.id; //クリック要素のid

	if (id === "double") {
		times = 2;
	} else if(id === "triple") {
		times = 3;
	} else {
		return;
	}

	// triple,doubleが存在しない場合スルー
	if(throwScores[targetThrow] === 50 || throwScores[targetThrow] === 0) {
		return;
	}

	// 直前の得点を掛け算する
	throwScores[targetThrow] = throwScores[targetThrow] * times;
	
	roundScores = calcRoundScore(throwScores);// ラウンドスコア更新
	currentRound = calcCurrentRound(targetThrow);// 前回の投数で計算する
	document.getElementById(`r${currentRound}-score`).innerHTML=roundScores[currentRound];
	// 総得点の更新
	totalScore = calcTotalScore();
	document.getElementById("total-score").innerHTML=totalScore;// 総得点ラベル更新

	// formを更新
}

// totalScoreはthrowScoreの合計として算出する
function calcTotalScore() {	
	totalScore = 0;
	// 総得点を更新
	//console.log(throwScores);
	for (i = 0; i<throwScores.length; i++) {
		totalScore += throwScores[i];
	}
	return totalScore;
}

// roundScoreはthrowScoreを3投区切りにした値から算出する
function calcRoundScore(throwScores) {
	let roundScores = [];
	for (i = 0; i<throwScores.length; i++) {
		round = Math.floor(i / 3) + 1;

		if(typeof roundScores[round] === "undefined") {
			roundScores[round] = throwScores[i];
		} else {
			roundScores[round] += throwScores[i];
		}
	}
	return roundScores;
}

// 投数から現在のラウンドを算出する
function calcCurrentRound(currentThrow) {
	// ラウンド判定
	// 3投投げ終えた時点では2を返す
	currentRound = Math.floor(currentThrow / 3) + 1;

	return currentRound;
}

// 得点ボタンにイベント付与
let scoreBtns = document.getElementsByClassName("score-btn");
//console.log(scoreBtns);
for(let i = 0; i < scoreBtns.length; i++){
	scoreBtns[i].addEventListener("click", scoresClick, false);
}

// double,tripleボタンにイベント付与
let timesBtns = document.getElementsByClassName("times-btn");
//console.log(timesBtns);
for(let i = 0; i < timesBtns.length; i++){
	timesBtns[i].addEventListener("click", timesClick, false);
}

document.myForm.submitBtn.addEventListener('click', function() {
 
 	let myForm = document.getElementById('myForm');
 	let formData = new FormData(myForm);

 	formData.append("totalScore", 100);
    myForm.submit();
 
});
