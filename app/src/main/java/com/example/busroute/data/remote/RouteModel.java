package com.example.busroute.data.remote;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dasari on 05/11/17.
 */

public class RouteModel {


    @SerializedName("routes")
    private Routes[] routes;

    public Routes[] getRoutes() {
        return routes;
    }

    public void setRoutes(Routes[] routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return "ClassPojo [routes = " + routes + "]";
    }


    public static class Routes implements Parcelable {
        @SerializedName("id")
        private String id;

        @SerializedName("stops")
        private Stops[] stops;

        @SerializedName("description")
        private String description;

        @SerializedName("name")
        private String name;

        @SerializedName("image")
        private String image;

        @SerializedName("accessible")
        private String accessible;

        protected Routes(Parcel in) {
            id = in.readString();
            description = in.readString();
            name = in.readString();
            image = in.readString();
            accessible = in.readString();
        }

        public static final Creator<Routes> CREATOR = new Creator<Routes>() {
            @Override
            public Routes createFromParcel(Parcel in) {
                return new Routes(in);
            }

            @Override
            public Routes[] newArray(int size) {
                return new Routes[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Stops[] getStops() {
            return stops;
        }

        public void setStops(Stops[] stops) {
            this.stops = stops;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAccessible() {
            return accessible;
        }

        public void setAccessible(String accessible) {
            this.accessible = accessible;
        }

        @Override
        public String toString() {
            return "ClassPojo [id = " + id + ", stops = " + stops + ", description = " + description + ", name = " + name + ", image = " + image + ", accessible = " + accessible + "]";
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(description);
            dest.writeString(name);
            dest.writeString(image);
            dest.writeString(accessible);
        }
    }

    public static class Stops implements Parcelable{
        @SerializedName("name")
        private String name;

        protected Stops(Parcel in) {
            name = in.readString();
        }

        public static final Creator<Stops> CREATOR = new Creator<Stops>() {
            @Override
            public Stops createFromParcel(Parcel in) {
                return new Stops(in);
            }

            @Override
            public Stops[] newArray(int size) {
                return new Stops[size];
            }
        };

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "ClassPojo [name = " + name + "]";
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
        }
    }
}
