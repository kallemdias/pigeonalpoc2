<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="ycLogEntry">
        <h2 class="jh-entity-heading" data-cy="ycLogEntryDetailsHeading"><span>YcLogEntry</span> {{ ycLogEntry.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Created</span>
          </dt>
          <dd>
            <span>{{ ycLogEntry.created | formatDate }}</span>
          </dd>
          <dt>
            <span>Service Name</span>
          </dt>
          <dd>
            <span>{{ ycLogEntry.serviceName }}</span>
          </dd>
          <dt>
            <span>Method</span>
          </dt>
          <dd>
            <span>{{ ycLogEntry.method }}</span>
          </dd>
          <dt>
            <span>Step</span>
          </dt>
          <dd>
            <span>{{ ycLogEntry.step }}</span>
          </dd>
          <dt>
            <span>Discription</span>
          </dt>
          <dd>
            <span>{{ ycLogEntry.discription }}</span>
          </dd>
          <dt>
            <span>Log Type</span>
          </dt>
          <dd>
            <span>{{ ycLogEntry.logType }}</span>
          </dd>
          <dt>
            <span>Racing Plan</span>
          </dt>
          <dd>
            <div v-if="ycLogEntry.racingPlan">
              <router-link :to="{ name: 'RacingPlanView', params: { racingPlanId: ycLogEntry.racingPlan.id } }">{{
                ycLogEntry.racingPlan.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="ycLogEntry.id"
          :to="{ name: 'YcLogEntryEdit', params: { ycLogEntryId: ycLogEntry.id } }"
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

<script lang="ts" src="./yc-log-entry-details.component.ts"></script>
