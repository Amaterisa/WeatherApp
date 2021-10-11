# WeatherApp
Weather app to show current weather (updated every 3 hours) and next weather's by every 3 hours for 5 days.

<img width="346" alt="Screen Shot 2021-10-11 at 10 34 27" src="https://user-images.githubusercontent.com/18215320/136808789-241cd7e8-e988-4ea4-8972-b602c2728bb4.png">

Was implemented as a challenge for a job interview.

Among the tecnologies used are: **Room** to cache the current weather, **LiveData** to keep the UI up to date, **RecyclerView** to show a list of the next weathers, **Retrofit** to request the weather from the OpenWeatherMap API and **CoroutineWorker** to schedule a request every 3 hours.

The city search feature is not yet implemented. There are only 3 cities to choose
