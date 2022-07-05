<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="weatherDataPoint">
        <h2 class="jh-entity-heading" data-cy="weatherDataPointDetailsHeading"><span>WeatherDataPoint</span> {{ weatherDataPoint.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Property</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.property }}</span>
          </dd>
          <dt>
            <span>Mid Night</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.midNight }}</span>
          </dd>
          <dt>
            <span>Mig Night Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.migNightRelevance }}</span>
          </dd>
          <dt>
            <span>One</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.one }}</span>
          </dd>
          <dt>
            <span>One Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.oneRelevance }}</span>
          </dd>
          <dt>
            <span>Two</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.two }}</span>
          </dd>
          <dt>
            <span>Two Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.twoRelevance }}</span>
          </dd>
          <dt>
            <span>Three</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.three }}</span>
          </dd>
          <dt>
            <span>Three Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.threeRelevance }}</span>
          </dd>
          <dt>
            <span>Four</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.four }}</span>
          </dd>
          <dt>
            <span>Four Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.fourRelevance }}</span>
          </dd>
          <dt>
            <span>Five</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.five }}</span>
          </dd>
          <dt>
            <span>Five Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.fiveRelevance }}</span>
          </dd>
          <dt>
            <span>Six</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.six }}</span>
          </dd>
          <dt>
            <span>Six Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.sixRelevance }}</span>
          </dd>
          <dt>
            <span>Seven</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.seven }}</span>
          </dd>
          <dt>
            <span>Seven Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.sevenRelevance }}</span>
          </dd>
          <dt>
            <span>Eight</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.eight }}</span>
          </dd>
          <dt>
            <span>Eight Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.eightRelevance }}</span>
          </dd>
          <dt>
            <span>Nine</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.nine }}</span>
          </dd>
          <dt>
            <span>Nine Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.nineRelevance }}</span>
          </dd>
          <dt>
            <span>Ten</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.ten }}</span>
          </dd>
          <dt>
            <span>Ten Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.tenRelevance }}</span>
          </dd>
          <dt>
            <span>Eleven</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.eleven }}</span>
          </dd>
          <dt>
            <span>Eleven Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.elevenRelevance }}</span>
          </dd>
          <dt>
            <span>Twelve</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.twelve }}</span>
          </dd>
          <dt>
            <span>Twelve Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.twelveRelevance }}</span>
          </dd>
          <dt>
            <span>Thirteen</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.thirteen }}</span>
          </dd>
          <dt>
            <span>Thirteen Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.thirteenRelevance }}</span>
          </dd>
          <dt>
            <span>Fourteen</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.fourteen }}</span>
          </dd>
          <dt>
            <span>Fourteen Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.fourteenRelevance }}</span>
          </dd>
          <dt>
            <span>Fifteen</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.fifteen }}</span>
          </dd>
          <dt>
            <span>Fifteen Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.fifteenRelevance }}</span>
          </dd>
          <dt>
            <span>Sixteen</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.sixteen }}</span>
          </dd>
          <dt>
            <span>Sixteen Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.sixteenRelevance }}</span>
          </dd>
          <dt>
            <span>Seventeen</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.seventeen }}</span>
          </dd>
          <dt>
            <span>Seventeen Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.seventeenRelevance }}</span>
          </dd>
          <dt>
            <span>Eighteen</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.eighteen }}</span>
          </dd>
          <dt>
            <span>Eighteen Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.eighteenRelevance }}</span>
          </dd>
          <dt>
            <span>Nineteen</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.nineteen }}</span>
          </dd>
          <dt>
            <span>Nineteen Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.nineteenRelevance }}</span>
          </dd>
          <dt>
            <span>Twenty</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.twenty }}</span>
          </dd>
          <dt>
            <span>Twenty Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.twentyRelevance }}</span>
          </dd>
          <dt>
            <span>Twenty One</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.twentyOne }}</span>
          </dd>
          <dt>
            <span>Twenty One Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.twentyOneRelevance }}</span>
          </dd>
          <dt>
            <span>Twenty Two</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.twentyTwo }}</span>
          </dd>
          <dt>
            <span>Twenty Two Relevance</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.twentyTwoRelevance }}</span>
          </dd>
          <dt>
            <span>Twenty Three</span>
          </dt>
          <dd>
            <span>{{ weatherDataPoint.twentyThree }}</span>
          </dd>
          <dt>
            <span>Check Point</span>
          </dt>
          <dd>
            <div v-if="weatherDataPoint.checkPoint">
              <router-link :to="{ name: 'CheckPointView', params: { checkPointId: weatherDataPoint.checkPoint.id } }">{{
                weatherDataPoint.checkPoint.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="weatherDataPoint.id"
          :to="{ name: 'WeatherDataPointEdit', params: { weatherDataPointId: weatherDataPoint.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./weather-data-point-details.component.ts"></script>
