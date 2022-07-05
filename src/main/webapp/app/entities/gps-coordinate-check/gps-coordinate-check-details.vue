<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="gpsCoordinateCheck">
        <h2 class="jh-entity-heading" data-cy="gpsCoordinateCheckDetailsHeading">
          <span>GpsCoordinateCheck</span> {{ gpsCoordinateCheck.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Lat Deg</span>
          </dt>
          <dd>
            <span>{{ gpsCoordinateCheck.latDeg }}</span>
          </dd>
          <dt>
            <span>Lat Min</span>
          </dt>
          <dd>
            <span>{{ gpsCoordinateCheck.latMin }}</span>
          </dd>
          <dt>
            <span>Lat Sec</span>
          </dt>
          <dd>
            <span>{{ gpsCoordinateCheck.latSec }}</span>
          </dd>
          <dt>
            <span>Lat Direction</span>
          </dt>
          <dd>
            <span>{{ gpsCoordinateCheck.latDirection }}</span>
          </dd>
          <dt>
            <span>Lng Deg</span>
          </dt>
          <dd>
            <span>{{ gpsCoordinateCheck.lngDeg }}</span>
          </dd>
          <dt>
            <span>Lng Min</span>
          </dt>
          <dd>
            <span>{{ gpsCoordinateCheck.lngMin }}</span>
          </dd>
          <dt>
            <span>Lng Sec</span>
          </dt>
          <dd>
            <span>{{ gpsCoordinateCheck.lngSec }}</span>
          </dd>
          <dt>
            <span>Lng Direction</span>
          </dt>
          <dd>
            <span>{{ gpsCoordinateCheck.lngDirection }}</span>
          </dd>
          <dt>
            <span>Lat Displayed Value</span>
          </dt>
          <dd>
            <span>{{ gpsCoordinateCheck.latDisplayedValue }}</span>
          </dd>
          <dt>
            <span>Lng Displayed Value</span>
          </dt>
          <dd>
            <span>{{ gpsCoordinateCheck.lngDisplayedValue }}</span>
          </dd>
          <dt>
            <span>Lat Decimal</span>
          </dt>
          <dd>
            <span>{{ gpsCoordinateCheck.latDecimal }}</span>
          </dd>
          <dt>
            <span>Lng Decimal</span>
          </dt>
          <dd>
            <span>{{ gpsCoordinateCheck.lngDecimal }}</span>
          </dd>
          <dt>
            <span>Link</span>
          </dt>
          <dd>
            <span>{{ gpsCoordinateCheck.link }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="gpsCoordinateCheck.id"
          :to="{ name: 'GpsCoordinateCheckEdit', params: { gpsCoordinateCheckId: gpsCoordinateCheck.id } }"
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

<script lang="ts" src="./gps-coordinate-check-details.component.ts"></script>
