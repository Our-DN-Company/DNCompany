function addEventListeners() {
  // userImage 클릭 시 userModal 표시/숨김
  const userImage = document.getElementById("userImage");
  if (userImage) {
    userImage.addEventListener("click", function () {
      const userModal = document.getElementById("userModal");
      if (userModal) {
        userModal.classList.toggle("show");
      } else {
        console.error("User modal not found");
      }
    });
  }

  // myPageButton 클릭 시 페이지 이동
  const myPageButton = document.getElementById("myPageButton");
  if (myPageButton) {
    myPageButton.addEventListener("click", function () {
      window.location.href = "/mypage";
    });
  }

  // 신규 회원 통계 버튼 클릭 시 차트 업데이트
  const newMembersButton = document.getElementById("newMembersButton");
  if (newMembersButton) {
    newMembersButton.addEventListener("click", function () {
      window.chart.data.datasets[0].hidden = false;
      window.chart.data.datasets[1].hidden = true;
      window.chart.update();
      setActiveButton("newMembersButton");
    });
  }

  // 전체 회원 통계 버튼 클릭 시 차트 업데이트
  const totalMembersButton = document.getElementById("totalMembersButton");
  if (totalMembersButton) {
    totalMembersButton.addEventListener("click", function () {
      window.chart.data.datasets[0].hidden = true;
      window.chart.data.datasets[1].hidden = false;
      window.chart.update();
      setActiveButton("totalMembersButton");
    });
  }
}




// 차트 js
function setActiveButton(buttonId) {
  document
      .querySelectorAll(".chart-button")
      .forEach((button) => button.classList.remove("active"));
  const activeButton = document.getElementById(buttonId);
  if (activeButton) {
    activeButton.classList.add("active");
  } else {
    console.error("Button with ID " + buttonId + " not found");
  }
}

function populatePostStatusTable() {
  const postStatusTableBody = document.querySelector(".post-status-table tbody");
  if (!postStatusTableBody) return;

  // 기존 내용 클리어
  postStatusTableBody.innerHTML = '';




  // boardCounts 데이터로 테이블 채우기
  boardCounts.forEach(countNumber  => {
    const row = document.createElement("tr");
    row.innerHTML = `
            <td>${countNumber.postDate}</td>
            <td>${countNumber.qnaCount}</td>
            <td>${countNumber.zipCount}</td>
            <td>${countNumber.eventCount}</td>
            <td>${countNumber.reportCount}</td>
            <td>${countNumber.helpCount}</td>
        `;
    postStatusTableBody.appendChild(row);
  });
}

// DOMContentLoaded 이벤트
document.addEventListener("DOMContentLoaded", function () {
  // 현재 날짜 표시
  const currentDate = new Date().toLocaleDateString();
  const currentDateElement = document.getElementById("currentDate");
  if (currentDateElement) {
    currentDateElement.textContent = currentDate;
  } else {
    console.error("Current date element not found");
  }

  // 차트 생성
  const canvas = document.getElementById("myChart");
  if (canvas) {
    const ctx = canvas.getContext("2d");

    // 요일에서 날자로 변경하였음 역순으로 뽑히는걸 뒤집을려고 사용했지맘ㄴ
    // 애초에 쿼리에 desc를 지우면 해결이라서 쿼리를 수정함
    // const UserCountsReverse = userCounts.slice().reverse();

    const labels = userCounts.map(countNumber => countNumber.chartDate);
    const newMembersData = userCounts.map(countNumber => countNumber.newMembers);
    const totalMembersData = userCounts.map(countNumber => countNumber.totalMembers);

    const data = {
      labels: labels,
      datasets: [
        {
          label: "신규 회원 수",
          data: newMembersData,
          backgroundColor: "rgba(75, 192, 192, 0.2)",
          borderColor: "rgba(75, 192, 192, 1)",
          borderWidth: 1,
        },
        {
          label: "전체 회원 수",
          data: totalMembersData,
          backgroundColor: "rgba(255, 99, 132, 0.2)",
          borderColor: "rgba(255, 99, 132, 1)",
          borderWidth: 1,
          hidden: true, // 초기에는 숨김
        },
      ],
    };

    const config = {
      type: "bar",
      data: data,
      options: {
        scales: {
          y: {
            beginAtZero: true,
          },
        },
        maintainAspectRatio: false,
      },
    };

    window.chart = new Chart(ctx, config);
  } else {
    console.error("Canvas element not found");
  }

  populatePostStatusTable(); // 보드 카운터 이벤트 리스너
  addEventListeners(); // 이벤트 리스너 추가
});



