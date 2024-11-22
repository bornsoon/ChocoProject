document.addEventListener("DOMContentLoaded", () => {
  const dogImage = document.getElementById("dog-image");
  const catImage = document.getElementById("cat-image");
  const weightInput = document.getElementById("weight-input");
  const statusSelect = document.getElementById("status-select");
  const calculateButton = document.getElementById("calculate-button");
  const calorieResult = document.getElementById("calorie-result");
  const inputsSection = document.querySelector(".inputs");
  const resultSection = document.querySelector(".result");

  let whoIsIt = true; // 선택한 동물 (강아지: true, 고양이: false)

  // 강아지와 고양이 상태별 상수값
  const dogFactors = {
    "4개월 미만": 3,
    "4개월~성견": 2,
    "미중성 성견": 1.8,
    "중성화 완료 성견": 1.6,
    "과체중 성견": 1.4,
    "비만 성견": 1.2,
    "임신 전반 42일간 성견": 2,
    "임신 후반 21일간 성견": 3,
    "노령견": 1.2
  };

  const catFactors = {
    "4개월 미만": 3,
    "4개월~6개월": 2.5,
    "7개월~12개월": 2,
    "미중성 성묘": 1.4,
    "중성화 완료 성묘": 1.2,
    "과체중 성묘": 1.1,
    "비만 성묘": 0.9,
    "임신 중인 성묘": 2.5,
    "노묘": 1
  };

  // 동물 선택 이벤트
  dogImage.addEventListener("click", () => setupCalculator(true));
  catImage.addEventListener("click", () => setupCalculator(false));

  calculateButton.addEventListener("click", () => {
    const weight = parseFloat(weightInput.value);
    const selectedFactor = parseFloat(statusSelect.value);

    if (isNaN(weight) || weight <= 0) {
      alert("몸무게를 올바르게 입력하세요.");
      return;
    }

    // 권장 칼로리 계산 공식
    const baseCalorie = (weight * 30) + 70;
    const recommendedCalorie = baseCalorie * selectedFactor;

    calorieResult.textContent = `${whoIsIt ? "강아지" : "고양이"}의 하루 권장 칼로리는 ${recommendedCalorie.toFixed(2)} kcal, 기본 에너지 요구량은 ${baseCalorie.toFixed(2)} kcal 입니다.`;
    resultSection.style.display = "block";
  });

  // 계산기 설정
  function setupCalculator(isDogSelection) {
    whoIsIt = isDogSelection;
    inputsSection.style.display = "block";
    resultSection.style.display = "none";

    // 상태 옵션 초기화
    statusSelect.innerHTML = "";
    const factors = whoIsIt ? dogFactors : catFactors;

    for (const [key, value] of Object.entries(factors)) {
      const option = document.createElement("option");
      option.value = value;
      option.textContent = key;
      statusSelect.appendChild(option);
    }

    weightInput.value = "";
    calorieResult.textContent = "";
  }
});