<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="relevanceHeader">
        <h2 class="jh-entity-heading" data-cy="relevanceHeaderDetailsHeading"><span>RelevanceHeader</span> {{ relevanceHeader.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Midnight</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.midnight }}</span>
          </dd>
          <dt>
            <span>One</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.one }}</span>
          </dd>
          <dt>
            <span>Two</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.two }}</span>
          </dd>
          <dt>
            <span>Three</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.three }}</span>
          </dd>
          <dt>
            <span>Four</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.four }}</span>
          </dd>
          <dt>
            <span>Five</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.five }}</span>
          </dd>
          <dt>
            <span>Six</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.six }}</span>
          </dd>
          <dt>
            <span>Seven</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.seven }}</span>
          </dd>
          <dt>
            <span>Eight</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.eight }}</span>
          </dd>
          <dt>
            <span>Nine</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.nine }}</span>
          </dd>
          <dt>
            <span>Ten</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.ten }}</span>
          </dd>
          <dt>
            <span>Eleven</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.eleven }}</span>
          </dd>
          <dt>
            <span>Twelve</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.twelve }}</span>
          </dd>
          <dt>
            <span>Thirteen</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.thirteen }}</span>
          </dd>
          <dt>
            <span>Fourteen</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.fourteen }}</span>
          </dd>
          <dt>
            <span>Fifteen</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.fifteen }}</span>
          </dd>
          <dt>
            <span>Sixteen</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.sixteen }}</span>
          </dd>
          <dt>
            <span>Seventeen</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.seventeen }}</span>
          </dd>
          <dt>
            <span>Eighteen</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.eighteen }}</span>
          </dd>
          <dt>
            <span>Nineteen</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.nineteen }}</span>
          </dd>
          <dt>
            <span>Twenty</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.twenty }}</span>
          </dd>
          <dt>
            <span>Twenty One</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.twentyOne }}</span>
          </dd>
          <dt>
            <span>Twenty Two</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.twentyTwo }}</span>
          </dd>
          <dt>
            <span>Twenty Three</span>
          </dt>
          <dd>
            <span>{{ relevanceHeader.twentyThree }}</span>
          </dd>
          <dt>
            <span>Weather Data Point</span>
          </dt>
          <dd>
            <div v-if="relevanceHeader.weatherDataPoint">
              <router-link :to="{ name: 'WeatherDataPointView', params: { weatherDataPointId: relevanceHeader.weatherDataPoint.id } }">{{
                relevanceHeader.weatherDataPoint.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="relevanceHeader.id"
          :to="{ name: 'RelevanceHeaderEdit', params: { relevanceHeaderId: relevanceHeader.id } }"
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

<script lang="ts" src="./relevance-header-details.component.ts"></script>
