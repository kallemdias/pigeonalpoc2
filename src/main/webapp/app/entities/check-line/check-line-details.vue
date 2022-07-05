<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="checkLine">
        <h2 class="jh-entity-heading" data-cy="checkLineDetailsHeading"><span>CheckLine</span> {{ checkLine.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Order</span>
          </dt>
          <dd>
            <span>{{ checkLine.order }}</span>
          </dd>
          <dt>
            <span>Date Time</span>
          </dt>
          <dd>
            <span>{{ checkLine.dateTime | formatDate }}</span>
          </dd>
          <dt>
            <span>Location Name</span>
          </dt>
          <dd>
            <span>{{ checkLine.locationName }}</span>
          </dd>
          <dt>
            <span>Lat Decimal</span>
          </dt>
          <dd>
            <span>{{ checkLine.latDecimal }}</span>
          </dd>
          <dt>
            <span>Lng Decimal</span>
          </dt>
          <dd>
            <span>{{ checkLine.lngDecimal }}</span>
          </dd>
          <dt>
            <span>Distance</span>
          </dt>
          <dd>
            <span>{{ checkLine.distance }}</span>
          </dd>
          <dt>
            <span>Distance Displayed Value</span>
          </dt>
          <dd>
            <span>{{ checkLine.distanceDisplayedValue }}</span>
          </dd>
          <dt>
            <span>Link</span>
          </dt>
          <dd>
            <span>{{ checkLine.link }}</span>
          </dd>
          <dt>
            <span>Weather Report</span>
          </dt>
          <dd>
            <div v-if="checkLine.weatherReport">
              <router-link :to="{ name: 'WeatherReportView', params: { weatherReportId: checkLine.weatherReport.id } }">{{
                checkLine.weatherReport.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="checkLine.id"
          :to="{ name: 'CheckLineEdit', params: { checkLineId: checkLine.id } }"
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

<script lang="ts" src="./check-line-details.component.ts"></script>
