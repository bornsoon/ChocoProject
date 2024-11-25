document.addEventListener("DOMContentLoaded", () => {
     const dogImage = document.getElementById("dog-image"); // 강아지 사진
     const catImage = document.getElementById("cat-image"); // 고양이 사진
     const sizeContainer = document.getElementById("size-container"); // 강아지 크기 부분 넣은 컨테이너
     const sizeSelect = document.getElementById("size-select"); // 강아지 크기 선택
     const birthInput = document.getElementById("birth-input"); // 생년월일 입력
     const calculateButton = document.getElementById("calculate-button"); // 계산하기 버튼
     const ageResult = document.getElementById("age-result"); // 결과
     const inputsSection = document.querySelector(".inputs"); // 입력
     const resultSection = document.querySelector(".result"); // 결과 출력
	 const calcAgeButton = document.querySelector(".calc-age-button");
	 const calcRecommendCalorieButton = document.querySelector(".calc-recommend-calorie-button");


     let whoIsIt = true; // 현재 모드 (강아지: true, 고양이: false)

	 // 클릭한 이미지를 강조하는 함수
	  function highlightSelectedImage(selectedImage) {
	      // 모든 이미지를 초기화
	      dogImage.classList.remove("selected");
	      catImage.classList.remove("selected");

	      // 선택된 이미지에 "selected" 클래스 추가
	      selectedImage.classList.add("selected");
	  }

	  dogImage.addEventListener("click", () => {
	      whoIsIt = true;
	      highlightSelectedImage(dogImage); // 강아지 이미지 강조
	      setupCalculator(true);
	  });

	  catImage.addEventListener("click", () => {
	      whoIsIt = false;
	      highlightSelectedImage(catImage); // 고양이 이미지 강조
	      setupCalculator(false);
	  });

	 
	   // 현재 URL 경로 확인
	   const currentPath = window.location.pathname;

	   // 초기 상태 설정
	   if (currentPath === "/calc/age") {
	     calcAgeButton.classList.remove("disabled-button");
	     calcRecommendCalorieButton.classList.add("disabled-button");
	   } else if (currentPath === "/calc/recommend-calorie") {
	     calcRecommendCalorieButton.classList.remove("disabled-button");
	     calcAgeButton.classList.add("disabled-button");
	   }


     calculateButton.addEventListener("click", () => { //계산하기 버튼 클릭
       const birth = birthInput.value.trim(); // 입력된 생년월일 상수화
       const today = new Date(); // 오늘 날짜 받아옴
       let year, month, day; // 년, 월, 일

			// 연도, 월, 일 추출 후 기본값 설정
       if (birth.length < 4 || birth.length === 5 || birth.length === 7 || birth.length > 8) {
					alert("올바른 생년월일을 입력하세요.");
 				return;
			}
			// 생년월일이 4~8자리인 경우 처리
			year = parseInt(birth.substring(0, 4)); // 연도 추출
			month = birth.length >= 6 ? parseInt(birth.substring(4, 6)) : 6; // 월은 6자리가 아니면 기본값 6
			day = birth.length === 8 ? parseInt(birth.substring(6, 8)) : 1; // 일은 8자리가 아니면 기본값 1


       const birthDate = new Date(year, month - 1, day);
       const ageInMonths = calculateAgeInMonths(today, birthDate);

       const humanAge = whoIsIt
         ? calculateDogHumanAge(ageInMonths, sizeSelect.value)
         : calculateCatHumanAge(ageInMonths);

       ageResult.textContent = whoIsIt
         ? `강아지 나이로 ${ageInMonths}개월, 사람 나이로 ${humanAge}세 입니다.`
         : `고양이 나이로 ${ageInMonths}개월, 사람 나이로 ${humanAge}세 입니다.`;

       resultSection.style.display = "block";
     });

	 function setupCalculator(isDog) {
	   // 입력 영역 및 결과 초기화
	   inputsSection.style.display = "block";
	   resultSection.style.display = "none";

	   // 강아지 크기 선택 표시 여부
	   sizeContainer.style.display = isDog ? "block" : "none";

	   // "계산하기" 버튼 표시
	   const calculateButtonContainer = document.querySelector(".calculate-button-container");
	   calculateButtonContainer.style.display = "block";

	   // 초기화
	   birthInput.value = "";
	   ageResult.textContent = "";
	 }

     function calculateAgeInMonths(today, birthDate) {
       const yearDiff = today.getFullYear() - birthDate.getFullYear();
       const monthDiff = today.getMonth() - birthDate.getMonth();
       return yearDiff * 12 + monthDiff + (today.getDate() >= 15 ? 1 : 0);
     }

     function calculateDogHumanAge(months, size) {
       const ageData = {
         1: 1, 2: 2, 3: 4, 4: 6, 5: 8, 6: 10,
         7: 11, 8: 12, 9: 13, 10: 14, 11: 15, 12: 16,
         13: 17, 14: 17, 15: 18, 16: 18, 17: 19,
         18: 19, 19: 20, 20: 20, 21: 21, 22: 22, 23: 23, 24: 24,
       };

       if (months <= 24) return ageData[months] || 0;

       const extraMonths = months - 24;
       const rates = { small: 0.4167, medium: 0.5, large: 0.5833 };
       const additionalHumanAge = Math.round(extraMonths * (rates[size] || 0));
       return 24 + additionalHumanAge;
     }

     function calculateCatHumanAge(months) {
       const ageData = {
         1: 2, 2: 3, 3: 5, 4: 6, 5: 8, 6: 9,
         7: 10, 8: 11, 9: 12, 10: 13, 11: 14, 12: 15,
         13: 16, 14: 17, 15: 17, 16: 18, 17: 19,
         18: 20, 19: 21, 20: 21, 21: 22, 22: 22, 23: 23, 24: 24,
       };

       if (months <= 24) return ageData[months] || 0;

       const extraMonths = months - 24;
       const additionalHumanAge = Math.round(extraMonths * 0.3333);
       return 24 + additionalHumanAge;
     }
   });s
