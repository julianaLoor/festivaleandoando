document.addEventListener("DOMContentLoaded", () => {
  // Inicializar el mapa
  const map = L.map("mapa").setView([40.416775, -3.70379], 6); // Coordenadas iniciales de Madrid

  // Añadir las capas de OpenStreetMap
  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
  }).addTo(map);

  // Agregar un marcador en Madrid
  L.marker([40.416775, -3.70379])
    .addTo(map)
    .bindPopup("Madrid, España")
    .openPopup();
});
