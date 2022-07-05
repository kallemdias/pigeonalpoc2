<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="weatherReport">
        <h2 class="jh-entity-heading" data-cy="weatherReportDetailsHeading"><span>WeatherReport</span> {{ weatherReport.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Initiated</span>
          </dt>
          <dd>
            <span>{{ weatherReport.initiated | formatDate }}</span>
          </dd>
          <dt>
            <span>Release Date Time</span>
          </dt>
          <dd>
            <span>{{ weatherReport.releaseDateTime | formatDate }}</span>
          </dd>
          <dt>
            <span>Checkpoints Distance</span>
          </dt>
          <dd>
            <span>{{ weatherReport.checkpointsDistance }}</span>
          </dd>
          <dt>
            <span>Alerts</span>
          </dt>
          <dd>
            <span>{{ weatherReport.alerts }}</span>
          </dd>
          <dt>
            <span>Racing Plan</span>
          </dt>
          <dd>
            <div v-if="weatherReport.racingPlan">
              <router-link :to="{ name: 'RacingPlanView', params: { racingPlanId: weatherReport.racingPlan.id } }">{{
                weatherReport.racingPlan.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="weatherReport.id"
          :to="{ name: 'WeatherReportEdit', params: { weatherReportId: weatherReport.id } }"
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

<script lang="ts" src="./weather-report-details.component.ts"></script>
