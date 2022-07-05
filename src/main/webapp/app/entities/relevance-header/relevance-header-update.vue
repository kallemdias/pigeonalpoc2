<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="pigeonalpoc2App.relevanceHeader.home.createOrEditLabel" data-cy="RelevanceHeaderCreateUpdateHeading">
          Create or edit a RelevanceHeader
        </h2>
        <div>
          <div class="form-group" v-if="relevanceHeader.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="relevanceHeader.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-midnight">Midnight</label>
            <input
              type="checkbox"
              class="form-check"
              name="midnight"
              id="relevance-header-midnight"
              data-cy="midnight"
              :class="{ valid: !$v.relevanceHeader.midnight.$invalid, invalid: $v.relevanceHeader.midnight.$invalid }"
              v-model="$v.relevanceHeader.midnight.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-one">One</label>
            <input
              type="checkbox"
              class="form-check"
              name="one"
              id="relevance-header-one"
              data-cy="one"
              :class="{ valid: !$v.relevanceHeader.one.$invalid, invalid: $v.relevanceHeader.one.$invalid }"
              v-model="$v.relevanceHeader.one.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-two">Two</label>
            <input
              type="checkbox"
              class="form-check"
              name="two"
              id="relevance-header-two"
              data-cy="two"
              :class="{ valid: !$v.relevanceHeader.two.$invalid, invalid: $v.relevanceHeader.two.$invalid }"
              v-model="$v.relevanceHeader.two.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-three">Three</label>
            <input
              type="checkbox"
              class="form-check"
              name="three"
              id="relevance-header-three"
              data-cy="three"
              :class="{ valid: !$v.relevanceHeader.three.$invalid, invalid: $v.relevanceHeader.three.$invalid }"
              v-model="$v.relevanceHeader.three.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-four">Four</label>
            <input
              type="checkbox"
              class="form-check"
              name="four"
              id="relevance-header-four"
              data-cy="four"
              :class="{ valid: !$v.relevanceHeader.four.$invalid, invalid: $v.relevanceHeader.four.$invalid }"
              v-model="$v.relevanceHeader.four.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-five">Five</label>
            <input
              type="checkbox"
              class="form-check"
              name="five"
              id="relevance-header-five"
              data-cy="five"
              :class="{ valid: !$v.relevanceHeader.five.$invalid, invalid: $v.relevanceHeader.five.$invalid }"
              v-model="$v.relevanceHeader.five.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-six">Six</label>
            <input
              type="checkbox"
              class="form-check"
              name="six"
              id="relevance-header-six"
              data-cy="six"
              :class="{ valid: !$v.relevanceHeader.six.$invalid, invalid: $v.relevanceHeader.six.$invalid }"
              v-model="$v.relevanceHeader.six.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-seven">Seven</label>
            <input
              type="checkbox"
              class="form-check"
              name="seven"
              id="relevance-header-seven"
              data-cy="seven"
              :class="{ valid: !$v.relevanceHeader.seven.$invalid, invalid: $v.relevanceHeader.seven.$invalid }"
              v-model="$v.relevanceHeader.seven.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-eight">Eight</label>
            <input
              type="checkbox"
              class="form-check"
              name="eight"
              id="relevance-header-eight"
              data-cy="eight"
              :class="{ valid: !$v.relevanceHeader.eight.$invalid, invalid: $v.relevanceHeader.eight.$invalid }"
              v-model="$v.relevanceHeader.eight.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-nine">Nine</label>
            <input
              type="checkbox"
              class="form-check"
              name="nine"
              id="relevance-header-nine"
              data-cy="nine"
              :class="{ valid: !$v.relevanceHeader.nine.$invalid, invalid: $v.relevanceHeader.nine.$invalid }"
              v-model="$v.relevanceHeader.nine.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-ten">Ten</label>
            <input
              type="checkbox"
              class="form-check"
              name="ten"
              id="relevance-header-ten"
              data-cy="ten"
              :class="{ valid: !$v.relevanceHeader.ten.$invalid, invalid: $v.relevanceHeader.ten.$invalid }"
              v-model="$v.relevanceHeader.ten.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-eleven">Eleven</label>
            <input
              type="checkbox"
              class="form-check"
              name="eleven"
              id="relevance-header-eleven"
              data-cy="eleven"
              :class="{ valid: !$v.relevanceHeader.eleven.$invalid, invalid: $v.relevanceHeader.eleven.$invalid }"
              v-model="$v.relevanceHeader.eleven.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-twelve">Twelve</label>
            <input
              type="checkbox"
              class="form-check"
              name="twelve"
              id="relevance-header-twelve"
              data-cy="twelve"
              :class="{ valid: !$v.relevanceHeader.twelve.$invalid, invalid: $v.relevanceHeader.twelve.$invalid }"
              v-model="$v.relevanceHeader.twelve.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-thirteen">Thirteen</label>
            <input
              type="checkbox"
              class="form-check"
              name="thirteen"
              id="relevance-header-thirteen"
              data-cy="thirteen"
              :class="{ valid: !$v.relevanceHeader.thirteen.$invalid, invalid: $v.relevanceHeader.thirteen.$invalid }"
              v-model="$v.relevanceHeader.thirteen.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-fourteen">Fourteen</label>
            <input
              type="checkbox"
              class="form-check"
              name="fourteen"
              id="relevance-header-fourteen"
              data-cy="fourteen"
              :class="{ valid: !$v.relevanceHeader.fourteen.$invalid, invalid: $v.relevanceHeader.fourteen.$invalid }"
              v-model="$v.relevanceHeader.fourteen.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-fifteen">Fifteen</label>
            <input
              type="checkbox"
              class="form-check"
              name="fifteen"
              id="relevance-header-fifteen"
              data-cy="fifteen"
              :class="{ valid: !$v.relevanceHeader.fifteen.$invalid, invalid: $v.relevanceHeader.fifteen.$invalid }"
              v-model="$v.relevanceHeader.fifteen.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-sixteen">Sixteen</label>
            <input
              type="checkbox"
              class="form-check"
              name="sixteen"
              id="relevance-header-sixteen"
              data-cy="sixteen"
              :class="{ valid: !$v.relevanceHeader.sixteen.$invalid, invalid: $v.relevanceHeader.sixteen.$invalid }"
              v-model="$v.relevanceHeader.sixteen.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-seventeen">Seventeen</label>
            <input
              type="checkbox"
              class="form-check"
              name="seventeen"
              id="relevance-header-seventeen"
              data-cy="seventeen"
              :class="{ valid: !$v.relevanceHeader.seventeen.$invalid, invalid: $v.relevanceHeader.seventeen.$invalid }"
              v-model="$v.relevanceHeader.seventeen.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-eighteen">Eighteen</label>
            <input
              type="checkbox"
              class="form-check"
              name="eighteen"
              id="relevance-header-eighteen"
              data-cy="eighteen"
              :class="{ valid: !$v.relevanceHeader.eighteen.$invalid, invalid: $v.relevanceHeader.eighteen.$invalid }"
              v-model="$v.relevanceHeader.eighteen.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-nineteen">Nineteen</label>
            <input
              type="checkbox"
              class="form-check"
              name="nineteen"
              id="relevance-header-nineteen"
              data-cy="nineteen"
              :class="{ valid: !$v.relevanceHeader.nineteen.$invalid, invalid: $v.relevanceHeader.nineteen.$invalid }"
              v-model="$v.relevanceHeader.nineteen.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-twenty">Twenty</label>
            <input
              type="checkbox"
              class="form-check"
              name="twenty"
              id="relevance-header-twenty"
              data-cy="twenty"
              :class="{ valid: !$v.relevanceHeader.twenty.$invalid, invalid: $v.relevanceHeader.twenty.$invalid }"
              v-model="$v.relevanceHeader.twenty.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-twentyOne">Twenty One</label>
            <input
              type="checkbox"
              class="form-check"
              name="twentyOne"
              id="relevance-header-twentyOne"
              data-cy="twentyOne"
              :class="{ valid: !$v.relevanceHeader.twentyOne.$invalid, invalid: $v.relevanceHeader.twentyOne.$invalid }"
              v-model="$v.relevanceHeader.twentyOne.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-twentyTwo">Twenty Two</label>
            <input
              type="checkbox"
              class="form-check"
              name="twentyTwo"
              id="relevance-header-twentyTwo"
              data-cy="twentyTwo"
              :class="{ valid: !$v.relevanceHeader.twentyTwo.$invalid, invalid: $v.relevanceHeader.twentyTwo.$invalid }"
              v-model="$v.relevanceHeader.twentyTwo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-twentyThree">Twenty Three</label>
            <input
              type="checkbox"
              class="form-check"
              name="twentyThree"
              id="relevance-header-twentyThree"
              data-cy="twentyThree"
              :class="{ valid: !$v.relevanceHeader.twentyThree.$invalid, invalid: $v.relevanceHeader.twentyThree.$invalid }"
              v-model="$v.relevanceHeader.twentyThree.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="relevance-header-weatherDataPoint">Weather Data Point</label>
            <select
              class="form-control"
              id="relevance-header-weatherDataPoint"
              data-cy="weatherDataPoint"
              name="weatherDataPoint"
              v-model="relevanceHeader.weatherDataPoint"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  relevanceHeader.weatherDataPoint && weatherDataPointOption.id === relevanceHeader.weatherDataPoint.id
                    ? relevanceHeader.weatherDataPoint
                    : weatherDataPointOption
                "
                v-for="weatherDataPointOption in weatherDataPoints"
                :key="weatherDataPointOption.id"
              >
                {{ weatherDataPointOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.relevanceHeader.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./relevance-header-update.component.ts"></script>
