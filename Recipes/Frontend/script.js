const BASE_URL = "http://localhost:8080/api/recipes";

window.onload = function () {
    fetchAllRecipes();

    const searchInput = document.getElementById("searchInput");
    searchInput.addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            searchRecipes();
        }
    });

    document.getElementById("searchButton").addEventListener("click", searchRecipes);
};

function fetchAllRecipes() {
    fetch(`${BASE_URL}/all`)
        .then(response => response.json())
        .then(displayRecipes)
        .catch(console.error);
}

function searchRecipes() {
    const searchTerm = document.getElementById("searchInput").value.trim();
    if (searchTerm === "") {
        fetchAllRecipes();
        return;
    }

    fetch(`${BASE_URL}/search?name=${encodeURIComponent(searchTerm)}`)
        .then(response => response.json())
        .then(displayRecipes)
        .catch(console.error);
}

function displayRecipes(recipes) {
    const container = document.getElementById("recipeContainer");
    container.innerHTML = "";

    if (!recipes.length) {
        container.innerHTML = "<p>No recipes found.</p>";
        return;
    }

    recipes.forEach(recipe => {
        const card = document.createElement("div");
        card.className = "recipeCard";

        card.innerHTML = `
            <h3>${recipe.name}</h3>
            <img src="${recipe.imageUrl}" alt="${recipe.name}">
            <p><strong>Category:</strong> ${recipe.category}</p>
            <p><strong>Area:</strong> ${recipe.area}</p>
            <button>View Details</button>
        `;

        card.querySelector("button").addEventListener("click", () => showDetails(recipe));
        container.appendChild(card);
    });
}

function showDetails(recipe) {
    const detailWindow = window.open("", "_blank", "width=500,height=600,scrollbars=yes");

    if (!detailWindow) {
        alert("Please allow pop-ups for this site to view recipe details.");
        return;
    }

    detailWindow.document.write(`
        <html>
        <head>
            <title>${recipe.name}</title>
            <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
            <style>
                body {
                    font-family: 'Poppins', sans-serif;
                    margin: 20px;
                    background: #f9fafb;
                    color: #333;
                }
                .card {
                    background: white;
                    border-radius: 12px;
                    box-shadow: 0 8px 20px rgba(0,0,0,0.1);
                    padding: 20px;
                    max-width: 450px;
                    margin: 0 auto;
                    text-align: center;
                }
                img {
                    width: 100%;
                    border-radius: 8px;
                    margin-bottom: 15px;
                }
                h2 {
                    color: #1e293b;
                    margin-bottom: 10px;
                }
                p {
                    text-align: justify;
                    line-height: 1.5;
                    font-size: 14px;
                    margin-bottom: 10px;
                }
                a {
                    display: inline-block;
                    margin-top: 10px;
                    color: white;
                    background: linear-gradient(135deg, #6366f1, #4f46e5);
                    padding: 8px 14px;
                    border-radius: 6px;
                    text-decoration: none;
                }
                a:hover {
                    background: linear-gradient(135deg, #4f46e5, #4338ca);
                }
            </style>
        </head>
        <body>
            <div class="card">
                <h2>${recipe.name}</h2>
                <img src="${recipe.imageUrl}" alt="${recipe.name}">
                <p><strong>Category:</strong> ${recipe.category}</p>
                <p><strong>Area:</strong> ${recipe.area}</p>
                <p>${recipe.instructions.replace(/\n/g, "<br>")}</p>
                ${recipe.youtubeUrl ? `<a href="${recipe.youtubeUrl}" target="_blank">Watch on YouTube</a>` : ""}
            </div>
        </body>
        </html>
    `);
}
