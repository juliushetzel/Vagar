package jhetzel.vagar;


import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * This Class is supposed to hold maps between a tag for
 * a {@link ViewModelHolder} and its matching target. As
 * the targets instance is reinstantiated, it should get
 * its tag injected to a {@link android.os.Bundle} so that
 * the {@link ViewModelBinder} can extract it and ask for
 * the targets matching {@link ViewModel} from the
 * {@link ViewModelProvider}.
 *
 * @param <T> should be a {@link android.app.Fragment} or
 *           {@link android.app.Activity}
 */
abstract class BundleTagInjector<T>{
    private final Map<String, WeakReference<T>> mTargetTagMap;
    private final String mBundleTagKey;

    /**
     * Creates a tag injector that will inject tags mapped to given
     * targets.
     *
     * @param bundleTagKey The {@link android.os.Bundle} key under
     *                     which the tags will be extracted and injected
     */
    BundleTagInjector(String bundleTagKey) {
        mBundleTagKey = bundleTagKey;
        mTargetTagMap = new WeakHashMap<>();
    }

    /**
     * Maps the given {@param tag} to a {@link java.lang.ref.WeakReference}
     * of the {@param target}.
     *
     * @param target The target which will be mapped to the {@param tag}.
     * @param tag The tag which the {@param target} will be mapped to.
     */
    void registerForInjection(T target, String tag){
        mTargetTagMap.put(tag, new WeakReference<>(target));
    }

    /**
     * Looks for a tag in the given {@link android.os.Bundle} and sets a
     * {@link java.lang.ref.WeakReference} of the {@param target} to it.
     *
     * This will happen only if the given tag has already been added via
     * {@link #registerForInjection} and not been removed with
     * {@link #unregisterFromInjection}.
     *
     * @param target The target which will be mapped to the tag given by
     *               the {@param bundle}.
     * @param bundle A {@link android.os.Bundle} containing a tag which
     *               the {@param target} will be mapped to.
     */
    protected void referenceTarget(T target, Bundle bundle){
        String tag = extractTag(bundle);
        if(tag != null && mTargetTagMap.containsKey(tag)){
            mTargetTagMap.put(tag, new WeakReference<T>(target));
        }
    }

    private String getTagFor(T target){
        for(Map.Entry<String, WeakReference<T>> entry : mTargetTagMap.entrySet()){
            if(target != null &&
                    target == entry.getValue().get()){
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Clears the {@link java.lang.ref.WeakReference} of the
     *
     * {@param target} but keeps its tag for further tracking.
     * @param target The target which reference should be cleared.
     */
    protected void dereference(T target){
        String tag = getTagFor(target);
        if(tag != null && mTargetTagMap.get(tag) != null){
            mTargetTagMap.get(tag).clear();
        }
    }

    /**
     * Searches for the tag mapped to the target and
     * injects it into the bundle.
     *
     * @param target The target which mapped tag should be
     *               injected into the {@param bundle}.
     * @param bundle The bundle where the tag which the
     *               {@param target} is mapped to will be
     *               injected.
     */
    protected void injectTag(T target, Bundle bundle){
        String tag = getTagFor(target);
        if(tag != null) {
            bundle.putString(mBundleTagKey, tag);
        }
    }

    /**
     * Unregisters a {@param target} so that even its tag will be
     * cleared.
     *
     * @param target The target which should be unregistered.
     */
    protected void unregisterFromInjection(T target) {
        String tag = getTagFor(target);
        if(tag != null) {
            mTargetTagMap.remove(tag);
        }
    }


    private String extractTag(Bundle bundle){
        return bundle != null ? bundle.getString(mBundleTagKey) : null;
    }
}
