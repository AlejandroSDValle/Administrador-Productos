import { PropsWithChildren } from 'react';

export default function ErrorMessage({ children }: PropsWithChildren) {
  return (
    <div className="text-center my-4 bg-red-100 border border-red-400 text-red-700 rounded-md p-3 flex items-center justify-center gap-2">
      <span>{children}</span>
    </div>
  );
}
