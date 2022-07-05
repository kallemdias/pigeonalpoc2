<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="checkPoint">
        <h2 class="jh-entity-heading" data-cy="checkPointDetailsHeading"><span>CheckPoint</span> {{ checkPoint.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Order</span>
          </dt>
          <dd>
            <span>{{ checkPoint.order }}</span>
          </dd>
          <dt>
            <span>Date Time</span>
          </dt>
          <dd>
            <span>{{ checkPoint.dateTime | formatDate }}</span>
          </dd>
          <dt>
            <span>Location Name</span>
          </dt>
          <dd>
            <span>{{ checkPoint.locationName }}</span>
          </dd>
          <dt>
            <span>Lat Decimal</span>
          </dt>
          <dd>
            <span>{{ checkPoint.latDecimal }}</span>
          </dd>
          <dt>
            <span>Lng Decimal</span>
          </dt>
          <dd>
            <span>{{ checkPoint.lngDecimal }}</span>
          </dd>
          <dt>
            <span>Distance</span>
          </dt>
          <dd>
            <span>{{ checkPoint.distance }}</span>
          </dd>
          <dt>
            <span>Distance Displayed Value</span>
          </dt>
          <dd>
            <span>{{ checkPoint.distanceDisplayedValue }}</span>
          </dd>
          <dt>
            <span>Link</span>
          </dt>
          <dd>
            <span>{{ checkPoint.link }}</span>
          </dd>
          <dt>
            <span>Alerts</span>
          </dt>
          <dd>
            <span>{{ checkPoint.alerts }}</span>
          </dd>
          <dt>
            <span>Check Line</span>
          </dt>
          <dd>
            <div v-if="checkPoint.checkLine">
              <router-link :to="{ name: 'CheckLineView', params: { checkLineId: checkPoint.checkLine.id } }">{{
                checkPoint.checkLine.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="checkPoint.id"
          :to="{ name: 'CheckPointEdit', params: { checkPointId: checkPoint.id } }"
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

<script lang="ts" src="./check-point-details.component.ts"></script>
