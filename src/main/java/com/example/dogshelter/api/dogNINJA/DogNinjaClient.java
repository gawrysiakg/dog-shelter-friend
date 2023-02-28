//package com.example.dogshelter.api.dogNINJA;
//
//public class DogNinjaClient {
//
//    OkHttpClient client = new OkHttpClient();
//
//    Request request = new Request.Builder()
//            .url("https://dogs-by-api-ninjas.p.rapidapi.com/v1/dogs?name=golden%20retriever")
//            .get()
//            .addHeader("X-RapidAPI-Key", "aefdf1e498msha55d6b97448974cp18f451jsn7403705fcbf1")
//            .addHeader("X-RapidAPI-Host", "dogs-by-api-ninjas.p.rapidapi.com")
//            .build();
//
//    Response response = client.newCall(request).execute();
//}
//
//
//
//    public List<TrelloBoardDto> getTrelloBoards() {
//        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getTrelloUser() + "/boards")
//                .queryParam("key", trelloConfig.getTrelloAppKey())
//                .queryParam("token", trelloConfig.getTrelloToken())
//                .queryParam("fields", "name,id")
//                .queryParam("lists", "all")
//                .build()
//                .encode()
//                .toUri();
//
//        try {
//            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
//            return Optional.ofNullable(boardsResponse)
//                    .map(Arrays::asList)
//                    .orElse(Collections.emptyList())
//                    .stream()
//                    .filter(p -> Objects.nonNull(p.getId()) && Objects.nonNull(p.getName()))
//                    //.filter(p -> p.getName().contains("Kodilla"))
//                    .collect(Collectors.toList());
//        } catch (RestClientException e) {
//            LOGGER.error(e.getMessage(), e);
//            return Collections.emptyList();
//        }