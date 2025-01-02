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

  const today = new Date();
  for (let i = 0; i < 7; i++) {
    const date = new Date(today);
    date.setDate(today.getDate() - i);

    const qnaCount = Math.floor(Math.random() * 10);
    const noticeCount = Math.floor(Math.random() * 10);
    const eventCount = Math.floor(Math.random() * 10);
    const reportCount = Math.floor(Math.random() * 10);
    const helpCount = Math.floor(Math.random() * 10);

    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${date.toISOString().split("T")[0]}</td>
      <td>${qnaCount}</td>
      <td>${noticeCount}</td>
      <td>${eventCount}</td>
      <td>${reportCount}</td>
      <td>${helpCount}</td>
    `;
    postStatusTableBody.appendChild(row);
  }
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
    const labels = [
      "Monday",
      "Tuesday",
      "Wednesday",
      "Thursday",
      "Friday",
      "Saturday",
      "Sunday",
    ];

    const newMembersData = [12, 19, 3, 5, 2, 3, 7];
    const totalMembersData = [65, 59, 80, 81, 56, 55, 40];

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

  populatePostStatusTable();
  addEventListeners(); // 이벤트 리스너 추가
});
